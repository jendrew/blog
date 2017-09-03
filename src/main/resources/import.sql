insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_SUPERADMIN');

insert into user (username, password, fullname, enabled, role_id) values ('jedrzej', '$2a$06$CzXAK3icLkBnpgi5zOTB.O/jzZqnxbPhtUQaxGZOhedahxAV2Iy4K','Jędrzej K.',true, 1);
insert into user (username, password, fullname, enabled, role_id) values ('anielka', '$2a$06$8FHgYeHducWsZkTbrOd8Te.1K4fnCDV2mZ1Z2iOweqC2VT.kITq86','Aniela Masna',true, 1);

insert into category ( name, displayname, positioninmenu) values ('książki', 'Książki', NULL );
insert into category ( name, displayname, positioninmenu) values ('film', 'Film', NULL );
insert into category ( name, displayname, positioninmenu) values ('diy', 'DIY', NULL );
insert into category ( name, displayname, positioninmenu) values ('tech', 'Tech', NULL );

insert into post (title, slug, body, datecreated, datemodified, author_id, category_id, published) values ('Pierwszy post', 'pierwszy-post', 'Lorem markdownum Achivae, ille **ferre mox** seque interdum similis ecce. *Bimembres* imbres se aequora filia retinacula [magno](http://polydecta.com/volvuntur.html), per formosior herba Corintho, Nilenube. Illic pertimuit pavida fusus: resisti rostro posuere auxilio partimfoedabis visa. Limine parentis ut Eleusin, fuit erat blanditiis color fit: quiunde, pinum. Omni det; est aut puro isque iamque quoniam ardent remis habuisseterribilem Quirini mihi?Non inexorabile sua [sub vota fata](http://figuramvolucrem.org/herbae.html) indeae siste additur pennisque confinia nomine? Reddidit inlaesos mansit.Fuerit summa: rursus meis tumulis. Loco mansit, eum, sed ambiguis iam haud huncterreat Aricinae, habebimus amplexa. Pignora quorum, bracchia tinguet!Nunc bacae [manusque](http://a.io/) iusta proxima, sibila **dei** terram certecapillos exhalari obscuraque ducat, nec tamen, corona. Parent aut caelo instant,longi gemit, vigili, pieros acta: nec decepit es telis est fratri tamen! Ipse**quaerit perfregit attritas** brevis pelle cum inscius quoque volenti sineaccipienda. Mensas ab hactenus faciente fuit scindunt **tellus**, stuppea suiset visa leves stratum hi nunc ex ingens. Sidere fine, Pleiadum praeferre peregitbracchia: et est Nereus.', '2017-07-20 23:48:29.851', '2017-07-20 23:48:29.828', 1, 1, TRUE );
insert into post (title, slug, body, datecreated, datemodified, author_id, category_id, published) values ('Drugi post', 'drugi-post', 'Lorem markdownum lapsu silvas, tua silet nostri puellas viso nostri Bacchei grata; illum Tmolo litus. Propago iacent, ore hoc calidumque optat utinam movet*pueri* generosam movere quinquennem neque truncoque Abarin! Dare seu ista,dore cognitus canamus, membrisque *quoque*. Mea ignis exit natura[tibi](http://animosmea.net/cursu.html), accede cum exemplum urbis erat signata.- Permaneo suumque membra latarumque- Effuge ab optetis dominum meorum expulit flamma- Salutem mallem quem talibus concresse sine ossa- Parent ore egere non necLaqueis fata dentes et lacrimae moratur; quod turbatum dixisse quamquam pectus!Videntur glacialis liceatque munus arva intravimus Cadmi vera, in aderat inquitostendit tanti, suos. Armis de Olympi fallax. Uni venenata per, paelex axem quaeMinyeia, hominis.Alte nulla succiso venerisque caritura Eurytion incalfacit deus cum tenusclauserat. Litora vectus: rotarum ungula moderamina quoque ferunt referentemfaciente ingratos Semeleia gentesque tenet dicentem.Hoc frustra miserabilis viri subito conspecta derexit, Europam, ter sonuere,Apolline neve. Commissaque illic fecitque mater! Eodem haustis alio fraternaquepavor mirabile porrexit serpere; *tum his* antrum inutilior pavidum, eiecitcurvantur ut Orpheus! Eripuit semper extinctum honorem Troianae concurreretnumen ait tingui visum turba medio venam Tyrrhenus **ille**. Nunc primoque,radice dea, Tenedos, hippomene praemonitus nimium; iam Coronida ligno?', '2017-07-20 23:58:29.851', '2017-07-20 23:58:29.828', 1, 1, TRUE );
insert into post (title, slug, body, datecreated, datemodified, author_id, category_id, published) values ('Trzeci post', 'trzeci-post', 'Lorem markdownum etera esset oculisque carinae. Per aethere urbi si debita! Faciemque abstinet dato quod potest dixit mille tantum propiusque sollicitatque aliquid excepit fatentem flumina dilectus, patruelibus enim. Hebe maxima deus, patres meo conscia carina.', '2017-07-20 23:59:29.851', '2017-07-20 23:59:29.828', 1, 1, FALSE );
insert into post (title, slug, body, datecreated, datemodified, author_id, category_id, published) values ('Czwarty post', 'czwarty-post', 'Lorem markdownum demittite illo artus utinam: labor corpora fovesque. Docta hocest expellitque Thisbe magis qualescumque oculos cursu Actaeona? Recurvis quossupplex tempore ministros et et munus postquam ducit.1. Hos Phrygum amore Ancaei infantibus te quid2. Rettulit natus viri Proreus meo exhortor Diomede3. Tela decimo nimbi dum in radice arborErat ait novis ista, poena et faciente obibat secuturo nostrasque tibi mea tequeet. Viros numinis victa femina, dumque fronte; **est habet madefacta** in.In Cyane doloris vestibus agitat. Mora vero imago Achelous habuit; illa vicina,pro fortes adsuetudine oves? *Fortibus amasse* pedum madefactus Titania. Quaeumero *tellus turba* quo pudici ta', '2017-07-21 00:59:29.851', '2017-07-21 00:59:29.828', 1, 1, TRUE );

insert into page (title, body) values ('Intro' , 'Strona intro, kurwo!');
insert into page (title, body) values ('Ciekawostki' , 'Strona ciekawostek, kurwo!');