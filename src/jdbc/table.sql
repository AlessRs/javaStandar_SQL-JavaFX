CREATE TABLE `lugar` (
  `id` int(11) NOT NULL auto_increment,
  `NombreResponsable` varchar(45) DEFAULT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `Router` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

select * from lugar;

insert into lugar (NombreResponsable, Direccion, Router) values 
    ('carlos', 'carlos@gmail.com', 'Toplink')
    ,('Manuel', 'Manuel@gmail.com', 'D-link')
    ,('Pablo', 'Pablo@gmail.com', 'Linksys')
    ,('Juan', 'Juan@gmail.com', 'Linksys')
    ,('Miguel','Miguel@gmail.com', 'D-link')
    ,('Luciano', 'Luciano@gmail.com', 'Linksys');