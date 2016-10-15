--------------------------------------------------------
--  File created - szerda-október-05-2016   
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
Insert into ONELETRAJZ.NYELVEK (NYELV) values ('német');
Insert into ONELETRAJZ.NYELVEK (NYELV) values ('orosz');
Insert into ONELETRAJZ.NYELVEK (NYELV) values ('portugál');
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
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Abaújszántó');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Abony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Abádszalók');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Adony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ajak');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ajka');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Albertirsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Alsózsolca');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Aszód');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Badacsonytomaj');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Baja');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Baktalórántháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balassagyarmat');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonalmádi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonboglár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonföldvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonfüred');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonfûzfõ');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonkenese');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balatonlelle');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balkány');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Balmazújváros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Barcs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Battonya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Beled');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Berettyóújfalu');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Berhida');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Besenyszög');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Biatorbágy');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bicske');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Biharkeresztes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bodajk');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bonyhád');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Borsodnádasd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Budakalász');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Budakeszi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Budapest');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Budaörs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bábolna');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bácsalmás');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bátaszék');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bátonyterenye');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Békés');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Békéscsaba');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bélapátfalva');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bóly');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Bük');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Cegléd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Celldömölk');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Cigánd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csanádpalota');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csenger');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csepreg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csongrád');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csorna');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csorvás');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csurgó');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Csákvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dabas');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Debrecen');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Demecser');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Derecske');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Devecser');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Diósd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dombrád');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dombóvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dorog');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunaföldvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunaharaszti');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunakeszi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunavarsány');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunavecse');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dunaújváros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Dévaványa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Edelény');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Eger');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Elek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Emõd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Encs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Enying');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ercsi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Esztergom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fegyvernek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fehérgyarmat');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Felsõzsolca');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fertõd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fertõszentmiklós');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fonyód');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Fót');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Füzesabony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Füzesgyarmat');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyomaendrõd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyula');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyál');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyömrõ');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyöngyös');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyöngyöspata');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyönk');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gyõr');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gárdony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Göd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gödöllõ');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Gönc');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajdúböszörmény');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajdúdorog');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajdúhadház');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajdúnánás');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajdúszoboszló');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajdúsámson');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hajós');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Halásztelek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Harkány');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hatvan');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Herend');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Heves');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hévíz');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Hódmezõvásárhely');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ibrány');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Igal');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Isaszeg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Izsák');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Jánoshalma');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Jánosháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Jánossomorja');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Jászapáti');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Jászberény');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Jászfényszaru');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Jászkisér');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Jászárokszállás');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kaba');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kadarkút');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kalocsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kaposvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kapuvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Karcag');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kazincbarcika');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kecel');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kecskemét');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kemecse');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kenderes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kerekegyháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kerepes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Keszthely');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kisbér');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kiskunfélegyháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kiskunhalas');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kiskunmajsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kisköre');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kiskõrös');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kistarcsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kistelek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kisvárda');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kisújszállás');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Komló');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Komádi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Komárom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kondoros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kozármisleny');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kunhegyes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kunszentmiklós');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kunszentmárton');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Körmend');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Körösladány');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Kõszeg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lajosmizse');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lengyeltóti');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lenti');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Letenye');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lábatlan');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lébény');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Létavértes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Lõrinci');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Maglód');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Makó');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Marcali');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Martfû');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Martonvásár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Medgyesegyháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mezõberény');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mezõcsát');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mezõhegyes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mezõkeresztes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mezõkovácsháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mezõkövesd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mezõtúr');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mindszent');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Miskolc');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mohács');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Monor');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mosonmagyaróvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mágocs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mándok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Máriapócs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mátészalka');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mélykút');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mór');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Mórahalom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyatád');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagybajom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyecsed');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagyhalász');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagykanizsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagykálló');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagykáta');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagykõrös');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagymaros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nagymányok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyergesújfalu');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyékládháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyíradony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyírbátor');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyíregyháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyírlugos');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyírmada');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nyírtelek');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Nádudvar');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Onga');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Orosháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Oroszlány');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pacsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Paks');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pannonhalma');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pilis');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Piliscsaba');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pilisvörösvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Polgár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Polgárdi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pomáz');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pusztaszabolcs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Putnok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pálháza');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pápa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pásztó');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pécel');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pécs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pécsvárad');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Pétervására');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Püspökladány');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Rakamaz');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Rudabánya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Rácalmás');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ráckeve');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Rákóczifalva');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Répcelak');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Rétság');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sajóbábony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sajószentpéter');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Salgótarján');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sarkad');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sellye');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Siklós');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Simontornya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Siófok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Solt');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Soltvadkert');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sopron');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szabadszállás');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szarvas');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szeged');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szeghalom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szekszárd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szendrõ');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szentendre');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szentes');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szentgotthárd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szentlõrinc');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szerencs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szigethalom');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szigetszentmiklós');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szigetvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szikszó');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szob');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szolnok');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szombathely');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Százhalombatta');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Szécsény');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Székesfehérvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sándorfalva');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sárbogárd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sárospatak');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sárvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sásd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sátoraljaújhely');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sülysáp');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Sümeg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tab');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tamási');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tapolca');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tata');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tatabánya');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszacsege');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszaföldvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszafüred');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszakécske');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszalök');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszavasvári');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tiszaújváros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tokaj');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tolna');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tompa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tura');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tápiószele');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tát');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Téglás');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tét');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tótkomlós');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Tököl');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Törökbálint');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Törökszentmiklós');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Túrkeve');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vaja');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vasvár');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vecsés');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Velence');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Veresegyház');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Verpelét');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Veszprém');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Villány');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Visegrád');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vác');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vámospércs');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Várpalota');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vásárosnamény');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vép');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Vésztõ');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zalaegerszeg');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zalakaros');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zalalövõ');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zalaszentgrót');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zamárdi');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zirc');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Zsámbék');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Záhony');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ács');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Érd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ócsa');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Ózd');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Örkény');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Újfehértó');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Újhartyán');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Újkígyós');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Újszász');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Üllõ');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Õrbottyán');
Insert into ONELETRAJZ.VAROSOK (NEV) values ('Õriszentpéter');
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
