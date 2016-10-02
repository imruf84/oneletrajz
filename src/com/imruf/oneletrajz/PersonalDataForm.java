package com.imruf.oneletrajz;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SuppressWarnings("serial")
public class PersonalDataForm extends HorizontalLayout implements Validable, SQLInsertable, SQLUpdateable {
	
	private TextField vezetekNevTF;
	private TextField keresztNevTF;
	private VarosokComboBox szuletesiHely;
	private DateField szuletesiIdo;
	private Object id;
	private Upload fotoUL;
	private File fotoFile = null;

	public PersonalDataForm(Object id) throws SQLException {
		this.id = id;
		initComponents();
	}
	
	private void initComponents() throws SQLException {
		
		FormLayout fl = new FormLayout();
		addComponent(fl);
		
		Embedded fotoImg = new Embedded();
		fotoImg.setWidth("100px");
		fotoImg.setHeight("150px");
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		addComponent(vl);
		vl.addComponent(fotoImg);
		
		fotoUL = new Upload();
		
		class ImageUploader implements Receiver, SucceededListener, StartedListener {
			
			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				
				FileOutputStream stream = null;
				
				try {
					
					// TODO: már létezõ fájl esetén a fájlnév léptetése, vagy egyedi mappába rakása
		            fotoFile = new File("./uploads/" + filename);
		            stream = new FileOutputStream(fotoFile);
		        } catch (final java.io.FileNotFoundException e) {
		            Notification.show("Hiba a kép feltöltése során:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		            return null;
		        }
		        return stream;
			}
			@Override
			public void uploadSucceeded(SucceededEvent event) {
				
				// Kép átméretezése.
				//try {
					//BufferedImage bfi = ImageIO.read(fotoFile);
					//BufferedImage rimg = Scalr.resize(bfi, Method.ULTRA_QUALITY, Mode.FIT_EXACT, 100, 150);
					
					//fotoImg.setSource(new FileResource(fotoFile));
					
					AtomicInteger w = new AtomicInteger(0);
					AtomicInteger h = new AtomicInteger(0);
				
					StreamResource resource = new StreamResource(
					        new StreamResource.StreamSource() {
					            @Override
					            public InputStream getStream() {
					                 try {
					                	 BufferedImage bfi = ImageIO.read(fotoFile);
					 					BufferedImage rimg = Scalr.resize(bfi, Method.ULTRA_QUALITY, Mode.FIT_EXACT, 100, 150);
					                	 //BufferedImage rimg = Scalr.resize(bfi, 100);
					 					
					 					//w.set(rimg.getWidth());
					 					//h.set(rimg.getHeight());
					                	 fotoImg.setWidth(rimg.getWidth() + "px");
					 					fotoImg.setHeight(rimg.getHeight() + "px");
					 					
					 					ByteArrayOutputStream imagebuffer = new ByteArrayOutputStream();
					                  ImageIO.write(rimg, "jpg", imagebuffer);
					                  
					                  /* Return a stream from the buffer. */
					                  return new ByteArrayInputStream(imagebuffer.toByteArray());
					                } catch (IOException e) {
					                    return null;
					                }
					            }
					        }, "filename.png");
					
					//System.out.println(w.get());
 					//System.out.println(h.get());
					fotoImg.setSource(resource);
				//} catch (IOException e) {}
			}
			@Override
			public void uploadStarted(StartedEvent event) {
				if (!event.getMIMEType().toLowerCase().endsWith("jpeg")) {
					Notification.show("Kizárólag jpeg kiterjesztésû fájlok tölthetõek fel!", Notification.Type.ERROR_MESSAGE);
					fotoUL.interruptUpload();
				}
				
			}
		}
		
		ImageUploader iul = new ImageUploader();
		fotoUL.setCaption("Fotó:");
		fotoUL.setImmediate(true);
		fotoUL.setReceiver(iul);
		fotoUL.addSucceededListener(iul);
		fotoUL.addStartedListener(iul);
		fotoUL.setButtonCaption("Kép kiválasztása...");
		fl.addComponent(fotoUL);
		
		
		vezetekNevTF = new TextField();
		vezetekNevTF.setCaption("Vezetéknév:");
		vezetekNevTF.setMaxLength(20);
		vezetekNevTF.setRequired(true);
		vezetekNevTF.setRequiredError("Vezetéknév megadása kötelezõ!");
		vezetekNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true, "Vezetéknév formátuma nem megfelelõ!"));
		vezetekNevTF.setWidth("20em");
		fl.addComponent(vezetekNevTF);
		
		keresztNevTF = new TextField();
		keresztNevTF.setCaption("Keresztnév:");
		keresztNevTF.setMaxLength(20);
		keresztNevTF.setRequired(true);
		keresztNevTF.setRequiredError("Keresztnév megadása kötelezõ!");
		keresztNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true, "Keresztnév formátuma nem megfelelõ!"));
		keresztNevTF.setWidth("20em");
		fl.addComponent(keresztNevTF);
		
		szuletesiHely = new VarosokComboBox();
		szuletesiHely.setCaption("Születési hely:");
		szuletesiHely.setNullSelectionAllowed(false);
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Születési hely megadása kötelezõ!");
		szuletesiHely.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ]{1,30}", true, "Születési hely formátuma nem megfelelõ!"));
		fl.addComponent(szuletesiHely);
		
		szuletesiIdo = new DateField();
		szuletesiIdo.setCaption("Születési idõ:");
		szuletesiIdo.setRequired(true);
		szuletesiIdo.setRequiredError("Születési idõ megadása kötelezõ!");
		szuletesiIdo.addValidator(new NullValidator("Születési idõ megadása kötelezõ!", false));
		fl.addComponent(szuletesiIdo);
		
		// Azonosító esetén kitöltjük adatokkal az ûrlapot.
		fillFieldsById();
	}
	
	private void fillFieldsById() throws SQLException {
		
		if (null == id) return;
		
		// Adatok lekérdezése.
		FreeformQuery query = new FreeformQuery("SELECT * FROM SZEMELYEK", ConnectionManager.getConnectionPool(), "ID");
		SQLContainer container = new SQLContainer(query);
		Item data = container.getItem(ConnectionManager.objectToRowId(id));

		// Adatok megjelenítése.
		vezetekNevTF.setValue((String) data.getItemProperty("VEZETEK_NEV").getValue());
		keresztNevTF.setValue((String) data.getItemProperty("KERESZT_NEV").getValue());
		szuletesiHely.setValue((String) data.getItemProperty("SZULETESI_HELY").getValue());
		try {
			szuletesiIdo.setValue(new SimpleDateFormat("yyyy.MM.dd").parse(((String)data.getItemProperty("SZULETESI_IDO").getValue())));
		} catch (ReadOnlyException | ConversionException | ParseException e) {
		}
	}
	
	@Override
	public boolean isValid() {
		return vezetekNevTF.isValid() && keresztNevTF.isValid() && szuletesiHely.isValid() && szuletesiIdo.isValid();
	}

	@Override
	public void toUpdate() throws SQLException {
		Connection c = ConnectionManager.getConnectionPool().reserveConnection();
		PreparedStatement ps = c.prepareStatement(
				"UPDATE SZEMELYEK SET "
						+ "VEZETEK_NEV='" + vezetekNevTF.getValue() + "',"
						+ "KERESZT_NEV='" + keresztNevTF.getValue() + "',"
						+ "SZULETESI_HELY='" + szuletesiHely.getValue() + "',"
						+ "SZULETESI_IDO='" + new SimpleDateFormat("yyyy.MM.dd").format(szuletesiIdo.getValue()) + "'"
						+ " WHERE ID=" + id);
		ps.executeUpdate();
		c.commit();
		
		ps.close();
	}

	@Override
	public void afterUpdate(Object id) {
	}

	@Override
	public Object toInsert() throws SQLException {
		
		Connection c = ConnectionManager.getConnectionPool().reserveConnection();
		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO SZEMELYEK (VEZETEK_NEV,KERESZT_NEV,SZULETESI_HELY,SZULETESI_IDO) VALUES ("
						+ "'" + vezetekNevTF.getValue() + "',"
						+ "'" + keresztNevTF.getValue() + "',"
						+ "'" + szuletesiHely.getValue() + "',"
						+ "'" + new SimpleDateFormat("yyyy.MM.dd").format(szuletesiIdo.getValue()) + "'"
						+ ")", 
						new String[] { "ID" });
		ps.executeUpdate();
		
		c.commit();
		
		return ps.getGeneratedKeys().next() ? ConnectionManager.objectToRowId(ps.getGeneratedKeys().getInt(1)) : -1;
	}

	@Override
	public void afterInsert(Object newID) {
	}
}
