package com.imruf.oneletrajz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

import com.imruf.oneletrajz.ConnectionManager;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

import oracle.sql.BLOB;

import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SuppressWarnings("serial")
public class PersonalDataForm extends HorizontalLayout implements Validable, SQLInsertable, SQLUpdateable, Closable, SQLSelectable {

	/**
	 * Vezetéknév beviteli mezõje.
	 */
	private TextField vezetekNevTF;
	/**
	 * Keresztnév beviteli mezõje.
	 */
	private TextField keresztNevTF;
	/**
	 * Születési hely beviteli mezõje.
	 */
	private VarosokComboBox szuletesiHely;
	/**
	 * Születési idõ dátumválasztója.
	 */
	private DateField szuletesiIdo;
	/**
	 * Aktuális személy azonosítója.
	 */
	private Object id;
	/**
	 * Személy fotóját tartalmazó fájl.
	 */
	private File fotoFile = null;
	/**
	 * Személy fotóját megjelenító objektum.
	 */
	private Embedded fotoImg;

	/**
	 * Konstruktor.
	 * 
	 * @param id
	 *            személy azonosítója
	 * @throws SQLException
	 *             kivétel
	 */
	public PersonalDataForm(Object id) throws SQLException {
		this.id = id;
		initComponents();
	}

	/**
	 * Komponens inicializálása.
	 * 
	 * @throws SQLException
	 *             kivétel
	 */
	private void initComponents() throws SQLException {

		FormLayout fl = new FormLayout();
		addComponent(fl);

		fotoImg = new Embedded();
		fotoImg.setWidth("100px");
		fotoImg.setHeight("150px");
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		addComponent(vl);
		vl.addComponent(fotoImg);

		Upload fotoUL = new Upload();

		class ImageUploader implements Receiver, SucceededListener {

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {

				if (!mimeType.toLowerCase().endsWith("jpeg")) {
					Notification.show("Kizárólag jpeg kiterjesztésû fájlok tölthetõek fel!",
							Notification.Type.ERROR_MESSAGE);
					fotoUL.interruptUpload();
					return new NullOutputStream();
				}

				try {

					// TODO: már létezõ fájl esetén a fájlnév léptetése, vagy
					// egyedi mappába rakása
					fotoFile = new File(generatePhotoFileName());
					return new FileOutputStream(fotoFile);
				} catch (final java.io.FileNotFoundException e) {
					Notification.show("Hiba a kép feltöltése során:\n" + e.getLocalizedMessage(),
							Notification.Type.ERROR_MESSAGE);
				}

				return new NullOutputStream();
			}

			@Override
			public void uploadSucceeded(SucceededEvent event) {

				try {
					int w = 100;
					int h = 150;
					BufferedImage bi = ImageIO.read(fotoFile);
					bi = Scalr.resize(bi, Method.ULTRA_QUALITY, Mode.AUTOMATIC, w, h);

					BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

					Graphics bg = bi2.getGraphics();
					bg.setColor(Color.WHITE);
					bg.fillRect(0, 0, w, h);
					bg.drawImage(bi, Math.abs((bi.getWidth() - w) / 2), Math.abs((bi.getHeight() - h) / 2), null);
					bg.dispose();
					ImageIO.write(bi2, "jpg", fotoFile);
					fotoImg.setSource(new FileResource(fotoFile));

				} catch (IOException e) {
					Notification.show("Hiba a kép feltöltése során:\n" + e.getLocalizedMessage(),
							Notification.Type.ERROR_MESSAGE);
				}

			}

		}

		ImageUploader iul = new ImageUploader();
		fotoUL.setCaption("Fotó:");
		fotoUL.setImmediate(true);
		fotoUL.setReceiver(iul);
		fotoUL.addSucceededListener(iul);
		fotoUL.setButtonCaption("Kép kiválasztása...");
		fl.addComponent(fotoUL);

		vezetekNevTF = new TextField();
		vezetekNevTF.setCaption("Vezetéknév:");
		vezetekNevTF.setMaxLength(20);
		vezetekNevTF.setRequired(true);
		vezetekNevTF.setRequiredError("Vezetéknév megadása kötelezõ!");
		vezetekNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true,
				"Vezetéknév formátuma nem megfelelõ!"));
		vezetekNevTF.setWidth("20em");
		fl.addComponent(vezetekNevTF);

		keresztNevTF = new TextField();
		keresztNevTF.setCaption("Keresztnév:");
		keresztNevTF.setMaxLength(20);
		keresztNevTF.setRequired(true);
		keresztNevTF.setRequiredError("Keresztnév megadása kötelezõ!");
		keresztNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true,
				"Keresztnév formátuma nem megfelelõ!"));
		keresztNevTF.setWidth("20em");
		fl.addComponent(keresztNevTF);

		szuletesiHely = new VarosokComboBox();
		szuletesiHely.setCaption("Születési hely:");
		szuletesiHely.setNullSelectionAllowed(false);
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Születési hely megadása kötelezõ!");
		szuletesiHely.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ]{1,30}", true,
				"Születési hely formátuma nem megfelelõ!"));
		fl.addComponent(szuletesiHely);

		szuletesiIdo = new DateField();
		szuletesiIdo.setCaption("Születési idõ:");
		szuletesiIdo.setRequired(true);
		szuletesiIdo.setRequiredError("Születési idõ megadása kötelezõ!");
		szuletesiIdo.addValidator(new NullValidator("Születési idõ megadása kötelezõ!", false));
		fl.addComponent(szuletesiIdo);

		// Azonosító esetén kitöltjük adatokkal az ûrlapot.
		getDataById();
	}

	/**
	 * Egyedi fájlnév generálása kép feltöltéséhez/megjelenítéséhez.
	 * 
	 * @return fájlnév
	 */
	private String generatePhotoFileName() {
		return VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/"
				+ VaadinSession.getCurrent().getSession().getId() + ".jpg";
	}

	@Override
	public void getDataById() throws SQLException {

		if (null == id)
			return;

		// Adatok lekérdezése.
		FreeformQuery query = new FreeformQuery("SELECT * FROM SZEMELYEK", ConnectionManager.getConnectionPool(), "ID");
		SQLContainer container = new SQLContainer(query);
		Item data = container.getItem(ConnectionManager.objectToRowId(id));

		// Adatok megjelenítése.
		vezetekNevTF.setValue((String) data.getItemProperty("VEZETEK_NEV").getValue());
		keresztNevTF.setValue((String) data.getItemProperty("KERESZT_NEV").getValue());
		szuletesiHely.setValue((String) data.getItemProperty("SZULETESI_HELY").getValue());
		try {
			szuletesiIdo.setValue(new SimpleDateFormat("yyyy.MM.dd.")
					.parse(((String) data.getItemProperty("SZULETESI_IDO").getValue())));

			BLOB image = (BLOB) data.getItemProperty("FOTO").getValue();
			if (null != image) {
				BufferedImage bi = ImageIO.read(image.getBinaryStream());
				fotoFile = new File(generatePhotoFileName());
				ImageIO.write(bi, "jpg", fotoFile);
				fotoImg.setSource(new FileResource(fotoFile));
			}
		} catch (ReadOnlyException | ConversionException | ParseException | IOException e) {
		}

	}

	@Override
	public boolean isValid() {
		return vezetekNevTF.isValid() && keresztNevTF.isValid() && szuletesiHely.isValid() && szuletesiIdo.isValid();
	}

	@Override
	public void toUpdate() throws SQLException, FileNotFoundException {
		
		// TODO: csak a ténylegesen módosított adatok értékének a módosítása
		Connection c = ConnectionManager.getConnection();

		PreparedStatement ps = c.prepareStatement(
				"UPDATE SZEMELYEK SET VEZETEK_NEV=?,KERESZT_NEV=?,SZULETESI_HELY=?,SZULETESI_IDO=?,FOTO=? WHERE ID="
						+ id);

		ps.setString(1, vezetekNevTF.getValue());
		ps.setString(2, keresztNevTF.getValue());
		ps.setString(3, szuletesiHely.getValue().toString());
		ps.setString(4, new SimpleDateFormat("yyyy.MM.dd.").format(szuletesiIdo.getValue()));
		if (fotoFile != null) {
			ps.setBinaryStream(5, new FileInputStream(fotoFile), (int) fotoFile.length());
		} else {
			ps.setNull(5, Types.BLOB);
		}

		ps.executeUpdate();
		c.commit();

		ps.close();
	}

	@Override
	public void afterUpdate(Object id) {
		onClose();
	}

	@Override
	public Object toInsert() throws SQLException, FileNotFoundException {

		Connection c = ConnectionManager.getConnection();

		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO SZEMELYEK (VEZETEK_NEV,KERESZT_NEV,SZULETESI_HELY,SZULETESI_IDO,FOTO) VALUES (?,?,?,?,?)",
				new String[] { "ID" });

		ps.setString(1, vezetekNevTF.getValue());
		ps.setString(2, keresztNevTF.getValue());
		ps.setString(3, szuletesiHely.getValue().toString());
		ps.setString(4, new SimpleDateFormat("yyyy.MM.dd.").format(szuletesiIdo.getValue()));
		if (fotoFile != null) {
			ps.setBinaryStream(5, new FileInputStream(fotoFile), (int) fotoFile.length());
		} else {
			ps.setNull(5, Types.BLOB);
		}

		ps.executeUpdate();
		c.commit();

		Object newID = ps.getGeneratedKeys().next() ? ConnectionManager.objectToRowId(ps.getGeneratedKeys().getInt(1))
				: -1;

		return newID;
	}

	@Override
	public void afterInsert(Object newID) {
		onClose();
	}

	@Override
	public void onClose() {
		if (fotoFile != null) {
			if (fotoFile.exists()) {
				fotoFile.delete();
			}
		}
	}
}
