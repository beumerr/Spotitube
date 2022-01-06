USE master
DROP DATABASE IF EXISTS Spotitube
CREATE DATABASE Spotitube
USE Spotitube

/* DROP TABLE [user] */
CREATE TABLE [user] (
    [id] int IDENTITY PRIMARY KEY,
    [fullname] varchar(75) NOT NULL,
    [user] varchar(50) NOT NULL UNIQUE,
    [password] varchar(75) NOT NULL,
    [token] varchar(150) NOT NULL
);

go

/* DROP TABLE [playlist] */
CREATE TABLE [playlist] (
    [id] int IDENTITY PRIMARY KEY,
    [name] varchar(75) NOT NULL,
);

go

/* DROP TABLE [owner] */
CREATE TABLE [owner] (
    [id] int IDENTITY PRIMARY KEY,
    [name] varchar(75) NOT NULL UNIQUE,
);

go

/* DROP TABLE [track] */
CREATE TABLE [track] (
    [id] int IDENTITY PRIMARY KEY,
    [title] varchar(150) NOT NULL,
	[owner] int NOT NULL,
	[duration] int NOT NULL,
	[album] varchar(75)NOT NULL,
	[playcount]	int	NOT NULL,
	[publicationDate] date NOT NULL,
	[description] varchar(255) NOT NULL,
	[offlineAvailable] bit	NOT NULL,
	FOREIGN KEY ([owner]) REFERENCES [owner](id)
)

go

/* DROP TABLE [playlisttrack] */
CREATE TABLE [playlisttrack] (
    [playlistid] int NOT NULL,
    [trackid] int NOT NULL,
	PRIMARY KEY ([playlistid], [trackId]),
	FOREIGN KEY ([playlistid]) REFERENCES [playlist](id) ON DELETE CASCADE,
	FOREIGN KEY ([trackid]) REFERENCES [track](id) ON DELETE CASCADE
)

go

/* DROP TABLE [playlisttrack] */
CREATE TABLE [userplaylist] (
    [userid] int NOT NULL,
    [playlistid] int NOT NULL,
	[isowner] bit NOT NULL,
	PRIMARY KEY ([userid], [playlistid]),
	UNIQUE ([userid], [playlistid], [isowner]),
	FOREIGN KEY ([userid]) REFERENCES [user](id) ON DELETE CASCADE,
	FOREIGN KEY ([playlistid]) REFERENCES [playlist](id) ON DELETE CASCADE
)