/* SELECT tables */

select * from [user]
select * from [userplaylist]
select * from [playlist]
select * from [playlisttrack]
select * from [track]
select * from [owner]


/* SELECT playlist with total duration count */
/* BUGGED */

SELECT DISTINCT p.[id], p.[name], up.[isowner], sum(t.[duration]) as totalDuration
FROM [playlist] p, [userplaylist] up, [user] u, [track] t, [playlisttrack] pt
WHERE u.[token] = '1234-1234-1234'
AND u.[id] = up.[userid]
AND p.[id] = up.[playlistid]
AND pt.[playlistid] = up.[playlistid]
AND pt.[trackid] = t.[id]
GROUP BY p.[id], p.[name], up.[isowner]

/* SELECT total duration */

SELECT sum(t.[duration]) as totalDuration
FROM [playlist] p, [userplaylist] up, [user] u, [track] t, [playlisttrack] pt
WHERE u.[token] = '1234-1234-1234'
AND u.[id] = up.[userid]
AND p.[id] = up.[playlistid]
AND pt.[playlistid] = up.[playlistid]
AND pt.[trackid] = t.[id];


/* SELECT playlist */

SELECT DISTINCT p.[id], p.[name], up.[isowner]
FROM [playlist] p, [userplaylist] up, [user] u
WHERE u.[token] = '1234-1234-1234'
AND u.[id] = up.[userid]
AND p.[id] = up.[playlistid]


/* SELECT track by playlistid and token */

SELECT t.[id], t.[title], o.[name] as performer, t.[duration], t.[album], t.[playcount], t.[publicationDate], t.[description], t.[offlineAvailable]
FROM [track] t, [playlisttrack] pt, [owner] o, [user] u
WHERE pt.[playlistid] = 1
AND pt.[trackid] = t.[id]
AND t.[owner] = o.[id]
AND u.[token] = '608ff7dc-27fb-4e0f-a7e6-418aee2cd57a'


/*-- INSERT playlist by token --*/

INSERT INTO [playlist]([name]) VALUES ('playlist name')

INSERT INTO [userplaylist]([userid], [playlistid], [isowner])
VALUES(
	(SELECT id FROM [user] u where u.token = '1234-1234-1234'), 
	(SELECT TOP 1 [id] FROM [playlist] ORDER BY [id] DESC), 
	1
);

/* DELETE playlist by id --*/
DELETE FROM [playlist] WHERE id = 1

/* Update playlist name by id --*/
UPDATE [playlist]
SET [name] = 'new name'
WHERE [id] = 1;

SELECT * FROM [track]
WHERE [id] NOT IN (
	SELECT t.[id]
	FROM [track] t, [playlisttrack] pt
	WHERE t.[id] = pt.[trackid]
	AND pt.[playlistid] = 2
)

SELECT * FROM [track] t
WHERE t.[id] NOT IN (
	SELECT t.[id]
	FROM [track] t, [playlisttrack] pt, [user] u, [userplaylist] up
	WHERE u.[token] = 'a135008f-ba75-434c-95c5-18205ead71b0'
	AND up.[playlistid] = 2
	AND t.[id] = pt.[trackid]
	AND pt.[playlistid] = 2
)


SELECT distinct t.[id], t.[title], o.[name] as performer, t.[duration], t.[album], t.[playcount], t.[publicationDate], t.[description], t.[offlineAvailable]
FROM [track] t, [playlisttrack] pt, [owner] o, [user] u
WHERE t.[id] NOT IN (
	SELECT t.[id]
	FROM [track] t, [playlisttrack] pt
	WHERE t.[id] = pt.[trackid]
	AND pt.[playlistid] = 1
)
AND pt.[trackid] = t.[id]
AND t.[owner] = o.[id]
AND u.[token] = '608ff7dc-27fb-4e0f-a7e6-418aee2cd57a'


select * from playlisttrack

DELETE FROM [playlisttrack] 
WHERE [playlistid] = 1
AND [trackid] = 1
AND 1 = (select count(*) FROM [user] u WHERE u.[token] = '1')

select * from [user]

SELECT 1 as isValidToken FROM [user] WHERE token = '2b4eb980-ad73-4a25-a8f0-0deb9a6dd2fa71'