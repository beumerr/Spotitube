INSERT INTO [user] ([fullname], [user], [password], [token])
VALUES	('Meron Brouwer', 'meron', 'MySuperSecretPassword12341', '1234-1234-1234'),
		('Thomas Beumer', 'thomas', 'qwerty12', '4321-4321-4321');

go

INSERT INTO [owner] ([name])
VALUES	('Rick Astley'), ('Jordy Bernal'), ('Zanger Rinus'), ('Mart Hoogkamer');

go

INSERT INTO [track] ([title], [owner], [duration], [album], [playcount], [publicationDate], [description], [offlineAvailable])
VALUES	('Never Gonna Give You Up', 1, 240, 'To good to be true', 1108368196, '20091025', 'The official video for “Never Gonna Give You Up” by Rick Astley', 1),
		('Qeu si qeu non', 2, 311, 'Que Si, Que No', 246418, '20080501', 'The official video for “Qeu si qeu non” by Jordy Bernal', 1),
		('Met Romana Op De Scooter', 3, 346, 'Oeter Oeter', 2294483, '20181022', 'Its back! The Official Video of Met Romana Op De Scooter performed by Zanger Rinus. All rights to original creator, Zanger Rinus.', 1),
		('Ik Ga Zwemmen', 4, 256, 'Ik Ga Zwemmen', 9897712, '20210714', 'Bekijk hier de Officiële videoclip van @Mart Hoogkamer "Ik Ga Zwemmen"', 1);

go 

INSERT INTO [playlist] ([name])
VALUES	('International Playlist'), ('Dutch playlist');

go

INSERT INTO [userplaylist] ([userid], [playlistid], [isowner])
VALUES	(1, 1, 1), (1, 2, 0),
		(2, 2, 1), (2, 1, 0);

go

INSERT INTO [playlisttrack] ([playlistid], [trackid])
VALUES	(1, 1), (1, 2), 
		(2, 3), (2, 4);