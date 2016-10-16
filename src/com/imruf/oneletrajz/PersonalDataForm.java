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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.CheckBox;

import oracle.sql.BLOB;

import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SuppressWarnings("serial")
public class PersonalDataForm extends HorizontalLayout implements Validable, SQLInsertable, SQLUpdateable, Closable, SQLSelectable {

	/**
	 * Vezetéknév beviteli mezõje.
	 */
	private TextField firstNameTF;
	/**
	 * Keresztnév beviteli mezõje.
	 */
	private TextField lastNameTF;
	/**
	 * Születési hely beviteli mezõje.
	 */
	private CitiesComboBox placeOfBirth;
	/**
	 * Születési idõ dátumválasztója.
	 */
	private DateField dateOfBirth;
	/**
	 * Aktuális személy azonosítója.
	 */
	private Object id;
	/**
	 * Személy fotóját tartalmazó fájl.
	 */
	private File photoFile = null;
	/**
	 * Személy fotóját megjelenító objektum.
	 */
	private Embedded photoImg;
	/**
	 * Kép törlésének a jelölõje.
	 */
	private CheckBox photoRemoveCB;

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

		photoImg = new Embedded();
		photoImg.setWidth("100px");
		photoImg.setHeight("150px");
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		addComponent(vl);
		vl.addComponent(photoImg);

		Upload photoUL = new Upload();

		class ImageUploader implements Receiver, SucceededListener {

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {

				if (!mimeType.toLowerCase().endsWith("jpeg")) {
					Notification.show("Kizárólag jpeg kiterjesztésû fájlok tölthetõek fel!",
							Notification.Type.ERROR_MESSAGE);
					photoUL.interruptUpload();
					return new NullOutputStream();
				}

				try {

					// TODO: már létezõ fájl esetén a fájlnév léptetése, vagy
					// egyedi mappába rakása
					photoFile = new File(generatePhotoFileName());
					return new FileOutputStream(photoFile);
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
					BufferedImage bi = ImageIO.read(photoFile);
					bi = Scalr.resize(bi, Method.ULTRA_QUALITY, Mode.AUTOMATIC, w, h);

					BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

					Graphics bg = bi2.getGraphics();
					bg.setColor(Color.WHITE);
					bg.fillRect(0, 0, w, h);
					bg.drawImage(bi, Math.abs((bi.getWidth() - w) / 2), Math.abs((bi.getHeight() - h) / 2), null);
					bg.dispose();
					ImageIO.write(bi2, "jpg", photoFile);
					photoImg.setSource(new FileResource(photoFile));

				} catch (IOException e) {
					Notification.show("Hiba a kép feltöltése során:\n" + e.getLocalizedMessage(),
							Notification.Type.ERROR_MESSAGE);
				}

			}

		}

		HorizontalLayout photoHL = new HorizontalLayout();
		photoHL.setSpacing(true);
		photoHL.setCaption("Fotó:");
		fl.addComponent(photoHL);
		
		ImageUploader iul = new ImageUploader();
		photoUL.setImmediate(true);
		photoUL.setReceiver(iul);
		photoUL.addSucceededListener(iul);
		photoUL.setButtonCaption("Kép kiválasztása...");
		photoHL.addComponent(photoUL);
		
		photoRemoveCB = new CheckBox("Töröl");
		photoHL.addComponent(photoRemoveCB);
		photoHL.setComponentAlignment(photoRemoveCB, Alignment.MIDDLE_CENTER);

		firstNameTF = new TextField();
		firstNameTF.setCaption("Vezetéknév:");
		firstNameTF.setMaxLength(20);
		firstNameTF.setRequired(true);
		firstNameTF.setRequiredError("Vezetéknév megadása kötelezõ!");
		firstNameTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true,
				"Vezetéknév formátuma nem megfelelõ!"));
		firstNameTF.setWidth("20em");
		fl.addComponent(firstNameTF);

		lastNameTF = new TextField();
		lastNameTF.setCaption("Keresztnév:");
		lastNameTF.setMaxLength(20);
		lastNameTF.setRequired(true);
		lastNameTF.setRequiredError("Keresztnév megadása kötelezõ!");
		lastNameTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true,
				"Keresztnév formátuma nem megfelelõ!"));
		lastNameTF.setWidth("20em");
		fl.addComponent(lastNameTF);

		placeOfBirth = new CitiesComboBox();
		placeOfBirth.setCaption("Születési hely:");
		placeOfBirth.setNullSelectionAllowed(false);
		placeOfBirth.setRequired(true);
		placeOfBirth.setRequiredError("Születési hely megadása kötelezõ!");
		placeOfBirth.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ]{1,30}", true,
				"Születési hely formátuma nem megfelelõ!"));
		fl.addComponent(placeOfBirth);

		dateOfBirth = new DateField();
		dateOfBirth.setCaption("Születési idõ:");
		dateOfBirth.setRequired(true);
		dateOfBirth.setRequiredError("Születési idõ megadása kötelezõ!");
		dateOfBirth.addValidator(new NullValidator("Születési idõ megadása kötelezõ!", false));
		fl.addComponent(dateOfBirth);

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
		firstNameTF.setValue((String) data.getItemProperty("VEZETEK_NEV").getValue());
		lastNameTF.setValue((String) data.getItemProperty("KERESZT_NEV").getValue());
		placeOfBirth.setValue((String) data.getItemProperty("SZULETESI_HELY").getValue());
		try {
			dateOfBirth.setValue(new SimpleDateFormat("yyyy.MM.dd.")
					.parse(((String) data.getItemProperty("SZULETESI_IDO").getValue())));

			BLOB image = (BLOB) data.getItemProperty("FOTO").getValue();
			if (null != image) {
				BufferedImage bi = ImageIO.read(image.getBinaryStream());
				photoFile = new File(generatePhotoFileName());
				ImageIO.write(bi, "jpg", photoFile);
				photoImg.setSource(new FileResource(photoFile));
			}
		} catch (ReadOnlyException | ConversionException | ParseException | IOException e) {
		}

	}

	@Override
	public boolean isValid() {
		return firstNameTF.isValid() && lastNameTF.isValid() && placeOfBirth.isValid() && dateOfBirth.isValid();
	}

	@Override
	public void toUpdate() throws SQLException, FileNotFoundException {
		
		// TODO: csak a ténylegesen módosított adatok értékének a módosítása
		Connection c = ConnectionManager.getConnection();

		PreparedStatement ps = c.prepareStatement(
				"UPDATE SZEMELYEK SET VEZETEK_NEV=?,KERESZT_NEV=?,SZULETESI_HELY=?,SZULETESI_IDO=?,FOTO=? WHERE ID="
						+ id);

		ps.setString(1, firstNameTF.getValue());
		ps.setString(2, lastNameTF.getValue());
		ps.setString(3, placeOfBirth.getValue().toString());
		ps.setString(4, new SimpleDateFormat("yyyy.MM.dd.").format(dateOfBirth.getValue()));
		if (photoFile != null && !photoRemoveCB.getValue()) {
			ps.setBinaryStream(5, new FileInputStream(photoFile), (int) photoFile.length());
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

		ps.setString(1, firstNameTF.getValue());
		ps.setString(2, lastNameTF.getValue());
		ps.setString(3, placeOfBirth.getValue().toString());
		ps.setString(4, new SimpleDateFormat("yyyy.MM.dd.").format(dateOfBirth.getValue()));
		if (photoFile != null) {
			ps.setBinaryStream(5, new FileInputStream(photoFile), (int) photoFile.length());
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
		if (photoFile != null) {
			if (photoFile.exists()) {
				photoFile.delete();
			}
		}
	}
}
