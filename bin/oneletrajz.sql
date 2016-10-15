--------------------------------------------------------
--  File created - szerda-okt�ber-05-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence DEMO_CUST_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."DEMO_CUST_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence DEMO_ORDER_ITEMS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."DEMO_ORDER_ITEMS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence DEMO_ORD_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."DEMO_ORD_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 11 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence DEMO_PROD_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."DEMO_PROD_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence DEMO_USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."DEMO_USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence NYELVTUDAS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."NYELVTUDAS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence NYELVTUDAS_SEQ1
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."NYELVTUDAS_SEQ1"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SZEMELYEK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."SZEMELYEK_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SZEMELYEK_SEQ1
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."SZEMELYEK_SEQ1"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SZEMELYEK_SEQ2
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."SZEMELYEK_SEQ2"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SZEMELYEK_SEQ3
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."SZEMELYEK_SEQ3"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SZEMELYEK_SEQ4
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."SZEMELYEK_SEQ4"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SZEMELYEK_SEQ5
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."SZEMELYEK_SEQ5"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SZEMELYEK_SEQ6
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."SZEMELYEK_SEQ6"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TABLE1_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."TABLE1_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TANULMANYOK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ONELETRAJZ"."TANULMANYOK_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table NYELVEK
--------------------------------------------------------

  CREATE TABLE "ONELETRAJZ"."NYELVEK" 
   (	"NYELV" VARCHAR2(10 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table NYELVTUDAS
--------------------------------------------------------

  CREATE TABLE "ONELETRAJZ"."NYELVTUDAS" 
   (	"ID" NUMBER, 
	"SZEMELY_ID" NUMBER, 
	"NYELV" VARCHAR2(10 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SZEMELYEK
--------------------------------------------------------

  CREATE TABLE "ONELETRAJZ"."SZEMELYEK" 
   (	"ID" NUMBER, 
	"VEZETEK_NEV" VARCHAR2(20 CHAR), 
	"KERESZT_NEV" VARCHAR2(20 CHAR), 
	"SZULETESI_HELY" VARCHAR2(30 CHAR), 
	"SZULETESI_IDO" VARCHAR2(12 BYTE), 
	"FOTO" BLOB DEFAULT null
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("FOTO") STORE AS BASICFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table TANULMANYOK
--------------------------------------------------------

  CREATE TABLE "ONELETRAJZ"."TANULMANYOK" 
   (	"ID" NUMBER, 
	"SZEMELY_ID" NUMBER, 
	"METTOL" NUMBER, 
	"MEDDIG" NUMBER DEFAULT NULL, 
	"INTEZMENY" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table VAROSOK
--------------------------------------------------------

  CREATE TABLE "ONELETRAJZ"."VAROSOK" 
   (	"NEV" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into ONELETRAJZ.NYELVEK
SET DEFINE OFF;
Insert into ONELETRAJZ.NYELVEK (NYELV) values ('angol');
Insert into ONELETRAJZ.NYELVEK (NYELV) values ('n�met');
Insert into ONELETRAJZ.NYELVEK (NYELV) values ('orosz');
Insert into ONELETRAJZ.NYELVEK (NYELV) values ('portug�l');
Insert into ONELETRAJZ.NYELVEK (NYELV) values ('spanyol');
REM INSERTING into ONELETRAJZ.NYELVTUDAS
SET DEFINE OFF;
REM INSERTING into ONELETRAJZ.SZEMELYEK
SET DEFINE OFF;
REM INSERTING into ONELETRAJZ.TANULMANYOK
SET DEFINE OFF;
REM INSERTING into ONELETRAJZ.VAROSOK
SET DEFINE OFF;
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Aba');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Aba�jsz�nt�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Abony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ab�dszal�k');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Adony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ajak');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ajka');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Albertirsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Als�zsolca');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Asz�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Badacsonytomaj');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Baja');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Baktal�r�nth�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balassagyarmat');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonalm�di');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonbogl�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonf�ldv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonf�red');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonf�zf�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonkenese');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonlelle');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balk�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balmaz�jv�ros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Barcs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Battonya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Beled');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Beretty��jfalu');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Berhida');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Besenysz�g');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Biatorb�gy');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bicske');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Biharkeresztes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bodajk');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bonyh�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Borsodn�dasd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Budakal�sz');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Budakeszi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Budapest');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Buda�rs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�bolna');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�csalm�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�tasz�k');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�tonyterenye');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�k�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�k�scsaba');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�lap�tfalva');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�ly');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('B�k');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Cegl�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Celld�m�lk');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Cig�nd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csan�dpalota');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csenger');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csepreg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csongr�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csorna');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csorv�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csurg�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Cs�kv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dabas');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Debrecen');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Demecser');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Derecske');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Devecser');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Di�sd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dombr�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Domb�v�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dorog');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunaf�ldv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunaharaszti');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunakeszi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunavars�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunavecse');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Duna�jv�ros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('D�vav�nya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Edel�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Eger');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Elek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Em�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Encs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Enying');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ercsi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Esztergom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fegyvernek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Feh�rgyarmat');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fels�zsolca');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fert�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fert�szentmikl�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fony�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('F�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('F�zesabony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('F�zesgyarmat');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyomaendr�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyula');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gy�l');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gy�mr�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gy�ngy�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gy�ngy�spata');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gy�nk');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gy�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('G�rdony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('G�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('G�d�ll�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('G�nc');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajd�b�sz�rm�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajd�dorog');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajd�hadh�z');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajd�n�n�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajd�szoboszl�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajd�s�mson');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Haj�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hal�sztelek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hark�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hatvan');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Herend');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Heves');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('H�v�z');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('H�dmez�v�s�rhely');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ibr�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Igal');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Isaszeg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Izs�k');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('J�noshalma');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('J�nosh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('J�nossomorja');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('J�szap�ti');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('J�szber�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('J�szf�nyszaru');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('J�szkis�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('J�sz�roksz�ll�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kaba');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kadark�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kalocsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kaposv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kapuv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Karcag');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kazincbarcika');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kecel');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kecskem�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kemecse');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kenderes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kerekegyh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kerepes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Keszthely');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kisb�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kiskunf�legyh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kiskunhalas');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kiskunmajsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kisk�re');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kisk�r�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kistarcsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kistelek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kisv�rda');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kis�jsz�ll�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Koml�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kom�di');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kom�rom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kondoros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Koz�rmisleny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kunhegyes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kunszentmikl�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kunszentm�rton');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('K�rmend');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('K�r�slad�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('K�szeg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lajosmizse');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lengyelt�ti');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lenti');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Letenye');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('L�batlan');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('L�b�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('L�tav�rtes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('L�rinci');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Magl�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mak�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Marcali');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Martf�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Martonv�s�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Medgyesegyh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mez�ber�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mez�cs�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mez�hegyes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mez�keresztes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mez�kov�csh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mez�k�vesd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mez�t�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mindszent');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Miskolc');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Moh�cs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Monor');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mosonmagyar�v�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('M�gocs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('M�ndok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('M�riap�cs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('M�t�szalka');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('M�lyk�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('M�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('M�rahalom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyat�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagybajom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyecsed');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyhal�sz');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagykanizsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyk�ll�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyk�ta');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyk�r�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagymaros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagym�nyok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyerges�jfalu');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ny�kl�dh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ny�radony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ny�rb�tor');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ny�regyh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ny�rlugos');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ny�rmada');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ny�rtelek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('N�dudvar');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Onga');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Orosh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Oroszl�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pacsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Paks');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pannonhalma');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pilis');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Piliscsaba');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pilisv�r�sv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Polg�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Polg�rdi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pom�z');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pusztaszabolcs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Putnok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('P�lh�za');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('P�pa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('P�szt�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('P�cel');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('P�cs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('P�csv�rad');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('P�terv�s�ra');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('P�sp�klad�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Rakamaz');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Rudab�nya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('R�calm�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('R�ckeve');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('R�k�czifalva');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('R�pcelak');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('R�ts�g');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Saj�b�bony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Saj�szentp�ter');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Salg�tarj�n');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sarkad');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sellye');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sikl�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Simontornya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Si�fok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Solt');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Soltvadkert');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sopron');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szabadsz�ll�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szarvas');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szeged');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szeghalom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szeksz�rd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szendr�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szentendre');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szentes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szentgotth�rd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szentl�rinc');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szerencs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szigethalom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szigetszentmikl�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szigetv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sziksz�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szob');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szolnok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szombathely');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sz�zhalombatta');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sz�cs�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sz�kesfeh�rv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('S�ndorfalva');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('S�rbog�rd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('S�rospatak');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('S�rv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('S�sd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('S�toralja�jhely');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('S�lys�p');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('S�meg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tab');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tam�si');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tapolca');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tata');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tatab�nya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszacsege');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszaf�ldv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszaf�red');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszak�cske');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszal�k');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszavasv�ri');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tisza�jv�ros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tokaj');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tolna');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tompa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tura');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�pi�szele');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�gl�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�tkoml�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�k�l');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�r�kb�lint');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�r�kszentmikl�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('T�rkeve');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vaja');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vasv�r');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vecs�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Velence');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Veresegyh�z');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Verpel�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Veszpr�m');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vill�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Visegr�d');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('V�c');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('V�mosp�rcs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('V�rpalota');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('V�s�rosnam�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('V�p');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('V�szt�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zalaegerszeg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zalakaros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zalal�v�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zalaszentgr�t');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zam�rdi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zirc');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zs�mb�k');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Z�hony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�cs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�rd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�csa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�zd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�rk�ny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�jfeh�rt�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�jharty�n');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�jk�gy�s');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�jsz�sz');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�ll�');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�rbotty�n');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('�riszentp�ter');
--------------------------------------------------------
--  DDL for Index NYELVTUDAS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ONELETRAJZ"."NYELVTUDAS_PK" ON "ONELETRAJZ"."NYELVTUDAS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index VAROSOK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ONELETRAJZ"."VAROSOK_PK" ON "ONELETRAJZ"."VAROSOK" ("NEV") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SZEMELYEK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ONELETRAJZ"."SZEMELYEK_PK" ON "ONELETRAJZ"."SZEMELYEK" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index TANULMANYOK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ONELETRAJZ"."TANULMANYOK_PK" ON "ONELETRAJZ"."TANULMANYOK" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index TABLE1_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ONELETRAJZ"."TABLE1_PK" ON "ONELETRAJZ"."NYELVEK" ("NYELV") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table NYELVEK
--------------------------------------------------------

  ALTER TABLE "ONELETRAJZ"."NYELVEK" ADD CONSTRAINT "TABLE1_PK" PRIMARY KEY ("NYELV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ONELETRAJZ"."NYELVEK" MODIFY ("NYELV" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TANULMANYOK
--------------------------------------------------------

  ALTER TABLE "ONELETRAJZ"."TANULMANYOK" MODIFY ("METTOL" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."TANULMANYOK" MODIFY ("SZEMELY_ID" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."TANULMANYOK" MODIFY ("INTEZMENY" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."TANULMANYOK" ADD CONSTRAINT "TANULMANYOK_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ONELETRAJZ"."TANULMANYOK" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table NYELVTUDAS
--------------------------------------------------------

  ALTER TABLE "ONELETRAJZ"."NYELVTUDAS" ADD CONSTRAINT "NYELVTUDAS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ONELETRAJZ"."NYELVTUDAS" MODIFY ("NYELV" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."NYELVTUDAS" MODIFY ("SZEMELY_ID" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."NYELVTUDAS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table VAROSOK
--------------------------------------------------------

  ALTER TABLE "ONELETRAJZ"."VAROSOK" ADD CONSTRAINT "VAROSOK_PK" PRIMARY KEY ("NEV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ONELETRAJZ"."VAROSOK" MODIFY ("NEV" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SZEMELYEK
--------------------------------------------------------

  ALTER TABLE "ONELETRAJZ"."SZEMELYEK" MODIFY ("SZULETESI_IDO" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."SZEMELYEK" MODIFY ("SZULETESI_HELY" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."SZEMELYEK" ADD CONSTRAINT "SZEMELYEK_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ONELETRAJZ"."SZEMELYEK" MODIFY ("KERESZT_NEV" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."SZEMELYEK" MODIFY ("VEZETEK_NEV" NOT NULL ENABLE);
  ALTER TABLE "ONELETRAJZ"."SZEMELYEK" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table NYELVTUDAS
--------------------------------------------------------

  ALTER TABLE "ONELETRAJZ"."NYELVTUDAS" ADD CONSTRAINT "NYELVTUDAS_FK1" FOREIGN KEY ("SZEMELY_ID")
	  REFERENCES "ONELETRAJZ"."SZEMELYEK" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TANULMANYOK
--------------------------------------------------------

  ALTER TABLE "ONELETRAJZ"."TANULMANYOK" ADD CONSTRAINT "TANULMANYOK_FK1" FOREIGN KEY ("SZEMELY_ID")
	  REFERENCES "ONELETRAJZ"."SZEMELYEK" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  DDL for Trigger NYELVTUDAS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ONELETRAJZ"."NYELVTUDAS_TRG" 
BEFORE INSERT ON NYELVTUDAS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NYELVTUDAS_SEQ1.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "ONELETRAJZ"."NYELVTUDAS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger SZEMELYEK_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ONELETRAJZ"."SZEMELYEK_TRG" 
BEFORE INSERT ON SZEMELYEK 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT SZEMELYEK_SEQ6.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "ONELETRAJZ"."SZEMELYEK_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TANULMANYOK_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ONELETRAJZ"."TANULMANYOK_TRG" 
BEFORE INSERT ON TANULMANYOK 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT TANULMANYOK_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "ONELETRAJZ"."TANULMANYOK_TRG" ENABLE;
