USE [master]
GO
/****** Object:  Database [AccountShopDB]    Script Date: 10/26/2023 6:42:35 PM ******/
CREATE DATABASE [AccountShopDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AccountShopDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\AccountShopDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AccountShopDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\AccountShopDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [AccountShopDB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AccountShopDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AccountShopDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AccountShopDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AccountShopDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AccountShopDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AccountShopDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [AccountShopDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AccountShopDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AccountShopDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AccountShopDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AccountShopDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AccountShopDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AccountShopDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AccountShopDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AccountShopDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AccountShopDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AccountShopDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AccountShopDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AccountShopDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AccountShopDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AccountShopDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AccountShopDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AccountShopDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AccountShopDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [AccountShopDB] SET  MULTI_USER 
GO
ALTER DATABASE [AccountShopDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AccountShopDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AccountShopDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AccountShopDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AccountShopDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AccountShopDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [AccountShopDB] SET QUERY_STORE = ON
GO
ALTER DATABASE [AccountShopDB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [AccountShopDB]
GO
/****** Object:  Table [dbo].[AccountList]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountList](
	[accountID] [int] IDENTITY(1,1) NOT NULL,
	[productID] [int] NOT NULL,
	[email] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[dueDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[accountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BalanceHistory]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BalanceHistory](
	[username] [varchar](50) NOT NULL,
	[status] [bit] NOT NULL,
	[amount] [int] NOT NULL,
	[reason] [varchar](50) NOT NULL,
	[date] [datetime] NOT NULL,
	[newBalance] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[username] [varchar](50) NOT NULL,
	[productID] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[total] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CatalogList]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CatalogList](
	[catalogID] [varchar](50) NOT NULL,
	[catalogDisplayName] [nvarchar](255) NOT NULL,
	[catalogImg] [varchar](1000) NOT NULL,
 CONSTRAINT [PK_CatalogList] PRIMARY KEY CLUSTERED 
(
	[catalogID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DepositHistory]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DepositHistory](
	[requestID] [int] NOT NULL,
	[username] [varchar](50) NOT NULL,
	[amount] [int] NOT NULL,
	[requestDate] [datetime] NOT NULL,
	[status] [bit] NOT NULL,
	[actionDate] [datetime] NOT NULL,
	[actionBy] [varchar](50) NOT NULL,
	[reason] [varchar](50) NOT NULL,
 CONSTRAINT [PK_DepositHistory] PRIMARY KEY CLUSTERED 
(
	[requestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DepositRequest]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DepositRequest](
	[requestID] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[amount] [int] NOT NULL,
	[date] [datetime] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrdersHistory]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrdersHistory](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[accountID] [int] NOT NULL,
	[productID] [int] NOT NULL,
	[email] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[dueDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductList]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductList](
	[catalogID] [varchar](50) NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[type] [varchar](50) NOT NULL,
	[price] [int] NOT NULL,
	[shortDetail] [nvarchar](255) NOT NULL,
	[longDetail] [nvarchar](1000) NOT NULL,
	[productID] [int] IDENTITY(1,1) NOT NULL,
	[productImg] [varchar](1000) NOT NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductRate]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductRate](
	[rateID] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[productID] [int] NOT NULL,
	[star] [int] NOT NULL,
	[comment] [nvarchar](255) NULL,
	[rateDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[rateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RoleInformation]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoleInformation](
	[roleID] [int] NOT NULL,
	[roleName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_RoleInformation] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserBalance]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserBalance](
	[username] [varchar](50) NOT NULL,
	[balance] [int] NOT NULL,
 CONSTRAINT [PK_UserBalance] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 10/26/2023 6:42:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[fullname] [nvarchar](50) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[role] [int] NOT NULL,
	[isVerificated] [bit] NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 1000000, N'Deposit', CAST(N'2023-07-09T11:19:21.023' AS DateTime), 1000000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-09T11:20:42.687' AS DateTime), 950000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-10T10:47:53.187' AS DateTime), 850000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 150000, N'Order', CAST(N'2023-07-08T09:39:12.680' AS DateTime), 350000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 50000, N'Order', CAST(N'2023-07-08T10:11:07.183' AS DateTime), 300000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 500000, N'Order', CAST(N'2023-07-13T07:39:37.983' AS DateTime), 610000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 100000, N'Deposit', CAST(N'2023-07-14T21:29:54.117' AS DateTime), 710000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-10T10:56:19.377' AS DateTime), 780000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 20000, N'Order', CAST(N'2023-07-10T11:01:47.503' AS DateTime), 760000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-10T10:46:45.957' AS DateTime), 900000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 1, 500000, N'Deposit', CAST(N'2023-07-08T09:33:10.217' AS DateTime), 500000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 20000, N'Order', CAST(N'2023-07-10T10:50:35.497' AS DateTime), 830000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 50000, N'Order', CAST(N'2023-07-08T10:20:58.857' AS DateTime), 250000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-10T11:14:14.033' AS DateTime), 710000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-10T11:30:29.767' AS DateTime), 660000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 100000, N'Deposit', CAST(N'2023-07-10T11:36:25.493' AS DateTime), 760000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-11T14:52:27.437' AS DateTime), 710000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-11T14:53:05.177' AS DateTime), 660000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 500000, N'Deposit', CAST(N'2023-07-12T10:07:24.283' AS DateTime), 1160000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 1, 5000000, N'Deposit', CAST(N'2023-07-14T21:29:54.527' AS DateTime), 5250000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 210000, N'Order', CAST(N'2023-07-14T21:30:30.273' AS DateTime), 5040000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 210000, N'Order', CAST(N'2023-07-14T21:30:30.290' AS DateTime), 5040000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 210000, N'Order', CAST(N'2023-07-14T21:30:30.320' AS DateTime), 5040000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 210000, N'Order', CAST(N'2023-07-14T21:30:30.357' AS DateTime), 5040000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 90000, N'Order', CAST(N'2023-07-14T21:31:28.213' AS DateTime), 4950000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 90000, N'Order', CAST(N'2023-07-14T21:31:28.233' AS DateTime), 4950000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 90000, N'Order', CAST(N'2023-07-14T21:31:28.263' AS DateTime), 4950000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 250000, N'Order', CAST(N'2023-07-14T21:32:19.323' AS DateTime), 4700000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 200000, N'Order', CAST(N'2023-07-15T15:15:15.790' AS DateTime), 510000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 200000, N'Order', CAST(N'2023-07-15T15:15:15.810' AS DateTime), 510000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 200000, N'Order', CAST(N'2023-07-15T15:15:15.847' AS DateTime), 510000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 120000, N'Order', CAST(N'2023-07-15T15:16:02.543' AS DateTime), 390000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 120000, N'Order', CAST(N'2023-07-15T15:16:02.587' AS DateTime), 390000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 120000, N'Order', CAST(N'2023-07-15T15:16:02.607' AS DateTime), 390000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 70000, N'Order', CAST(N'2023-07-15T15:24:34.170' AS DateTime), 320000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 70000, N'Order', CAST(N'2023-07-15T15:24:34.190' AS DateTime), 320000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 70000, N'Order', CAST(N'2023-07-15T15:27:35.027' AS DateTime), 4630000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 70000, N'Order', CAST(N'2023-07-15T15:27:35.043' AS DateTime), 4630000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 70000, N'Order', CAST(N'2023-07-15T15:27:35.077' AS DateTime), 4630000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 150000, N'Order', CAST(N'2023-07-15T15:30:28.920' AS DateTime), 4480000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 150000, N'Order', CAST(N'2023-07-15T15:30:46.207' AS DateTime), 4480000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 150000, N'Order', CAST(N'2023-07-15T15:30:49.993' AS DateTime), 4480000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 90000, N'Order', CAST(N'2023-07-15T15:37:45.797' AS DateTime), 4250000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 90000, N'Order', CAST(N'2023-07-15T15:37:45.827' AS DateTime), 4250000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 90000, N'Order', CAST(N'2023-07-15T15:37:45.880' AS DateTime), 4250000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 200000, N'Deposit', CAST(N'2023-07-16T23:11:54.800' AS DateTime), 520000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 200000, N'Order', CAST(N'2023-07-16T23:21:46.820' AS DateTime), 320000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 200000, N'Order', CAST(N'2023-07-16T23:21:46.847' AS DateTime), 320000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 200000, N'Order', CAST(N'2023-07-16T23:21:46.897' AS DateTime), 320000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 1, 500000, N'Deposit', CAST(N'2023-07-16T23:57:59.640' AS DateTime), 4750000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-07-12T23:56:40.927' AS DateTime), 1110000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 210000, N'Order', CAST(N'2023-07-16T23:24:58.147' AS DateTime), 110000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 1000000, N'Deposit', CAST(N'2023-07-16T23:25:21.623' AS DateTime), 1110000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 70000, N'Order', CAST(N'2023-07-15T15:24:34.223' AS DateTime), 320000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 140000, N'Order', CAST(N'2023-07-15T15:35:05.740' AS DateTime), 4340000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 140000, N'Order', CAST(N'2023-07-15T15:35:05.763' AS DateTime), 4340000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 140000, N'Order', CAST(N'2023-07-15T15:35:05.810' AS DateTime), 4340000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 140000, N'Order', CAST(N'2023-07-15T15:35:05.827' AS DateTime), 4340000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 50000, N'Order', CAST(N'2023-07-17T09:43:32.887' AS DateTime), 4700000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'steve', 0, 3000000, N'Order', CAST(N'2023-07-17T10:29:24.450' AS DateTime), 1700000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 500000, N'Deposit', CAST(N'2023-07-19T22:42:18.423' AS DateTime), 1610000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 200000, N'Deposit', CAST(N'2023-07-19T22:45:16.973' AS DateTime), 1810000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 170000, N'Order', CAST(N'2023-07-19T22:45:32.853' AS DateTime), 1640000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 1, 200000, N'Deposit', CAST(N'2023-08-28T21:55:30.273' AS DateTime), 1840000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 100000, N'Order', CAST(N'2023-09-09T15:02:52.287' AS DateTime), 1740000)
INSERT [dbo].[BalanceHistory] ([username], [status], [amount], [reason], [date], [newBalance]) VALUES (N'tranhiwp', 0, 50000, N'Order', CAST(N'2023-09-21T16:09:39.193' AS DateTime), 1690000)
GO
INSERT [dbo].[CatalogList] ([catalogID], [catalogDisplayName], [catalogImg]) VALUES (N'canva', N'Canva Pro', N'https://cdn.sforum.vn/sforum/wp-content/uploads/2022/12/tai-canva-0.jpg')
INSERT [dbo].[CatalogList] ([catalogID], [catalogDisplayName], [catalogImg]) VALUES (N'chatgpt', N'ChatGPT', N'https://shsroundtable.com/wp-content/uploads/2023/01/chat-gpt-logo.jpg')
INSERT [dbo].[CatalogList] ([catalogID], [catalogDisplayName], [catalogImg]) VALUES (N'facebook', N'Facebook', N'https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Facebook_Logo_%282019%29.png/1200px-Facebook_Logo_%282019%29.png')
INSERT [dbo].[CatalogList] ([catalogID], [catalogDisplayName], [catalogImg]) VALUES (N'ggdrive', N'Google Drive Unlimited', N'https://zeevector.com/wp-content/uploads/Google-Drive-Logo-PNG.png')
INSERT [dbo].[CatalogList] ([catalogID], [catalogDisplayName], [catalogImg]) VALUES (N'gmail', N'Gmail', N'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Gmail_icon_%282020%29.svg/1024px-Gmail_icon_%282020%29.svg.png')
INSERT [dbo].[CatalogList] ([catalogID], [catalogDisplayName], [catalogImg]) VALUES (N'netflix', N'Netflix', N'https://images.ctfassets.net/4cd45et68cgf/Rx83JoRDMkYNlMC9MKzcB/2b14d5a59fc3937afd3f03191e19502d/Netflix-Symbol.png?w=700&h=456')
INSERT [dbo].[CatalogList] ([catalogID], [catalogDisplayName], [catalogImg]) VALUES (N'quizlet', N'Quizlet Plus', N'https://logos-download.com/wp-content/uploads/2019/11/Quizlet_Logo.png')
INSERT [dbo].[CatalogList] ([catalogID], [catalogDisplayName], [catalogImg]) VALUES (N'youtube', N'Youtube Premium', N'https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/YouTube_full-color_icon_%282017%29.svg/2560px-YouTube_full-color_icon_%282017%29.svg.png')
GO
INSERT [dbo].[DepositHistory] ([requestID], [username], [amount], [requestDate], [status], [actionDate], [actionBy], [reason]) VALUES (4, N'tranhiwp', 200000, CAST(N'2023-07-20T00:03:31.833' AS DateTime), 1, CAST(N'2023-08-28T21:55:30.273' AS DateTime), N'admin', N'Completed payment!')
GO
SET IDENTITY_INSERT [dbo].[DepositRequest] ON 

INSERT [dbo].[DepositRequest] ([requestID], [username], [amount], [date]) VALUES (5, N'tranhiwp', 5000000, CAST(N'2023-10-09T16:38:03.027' AS DateTime))
SET IDENTITY_INSERT [dbo].[DepositRequest] OFF
GO
SET IDENTITY_INSERT [dbo].[OrdersHistory] ON 

INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (14, N'steve', 22, 16, N'JCcKqKhCDwqKdQr', N'MzESDDHPyHzLpHH', CAST(N'2023-11-18T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (16, N'steve', 25, 15, N'FGVHBvsdxvivbs', N'YGBdsvxzndfbzb', CAST(N'2023-10-07T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (17, N'steve', 26, 15, N'gradsgfdbzcbsfg', N'sdzcbcxbdzxvzdx', CAST(N'2024-01-01T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (19, N'tranhiwp', 29, 15, N'wezdhrxfjcgk', N'AGSgzfbdngx', CAST(N'2023-11-04T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (20, N'tranhiwp', 30, 15, N'ewfasdxgfmcbvgf', N'sdhfznxgfmchgnfbd', CAST(N'2023-10-21T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (21, N'tranhiwp', 31, 15, N'hmgnfbd', N'gfnvcb', CAST(N'2023-10-07T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (22, N'tranhiwp', 17, 14, N'XvVObnJblKgDlsC', N'vwHRQlsTIHJyetv', CAST(N'2023-08-11T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (23, N'tranhiwp', 32, 15, N'fsdgzhxvmbnb', N'fsdgbcxnvmbnb', CAST(N'2023-09-02T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (24, N'tranhiwp', 28, 14, N'tedgjvnxcadzhn', N'SBfgdnxvcbxdn', CAST(N'2023-11-04T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (25, N'tranhiwp', 33, 15, N'wegrhtjgjgf', N'gzdhxmchn', CAST(N'2023-08-25T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (26, N'tranhiwp', 34, 15, N'sdfgxhvmngxz', N'sfdghxnhngfzfda', CAST(N'2023-10-28T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (28, N'tranhiwp', 42, 15, N'sdfghjhgfdfsgh', N'hmgfsdfghhg1', CAST(N'2023-10-06T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (29, N'tranhiwp', 43, 15, N'dshbsghnc', N'dfmcsf', CAST(N'2023-10-06T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (30, N'tranhiwp', 59, 15, N'fdghjgdfsafhj', N'hgfadsfjfhdgsfg', CAST(N'2023-09-09T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (31, N'tranhiwp', 67, 25, N'kjhgfsfjdhgsvhbn', N'csvhfxbtvcedfvghcb', CAST(N'2023-12-09T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (32, N'steve', 70, 15, N'retyuighjfdsadfgh', N'dfghjkgfdzsfgh', CAST(N'2023-12-16T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (33, N'steve', 71, 15, N'dfghjhgfsfbnf', N'yghnbfvdcdhjhgv', CAST(N'2024-01-12T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (34, N'steve', 78, 16, N'djjfhdgsacfvgbhnj', N'hgfdfsdghjkmjnhbgvfdcs', CAST(N'2024-02-14T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (35, N'steve', 64, 19, N'dfghjfdgsfdghj', N'dsfghjgfdsfghj', CAST(N'2023-10-20T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (36, N'steve', 63, 14, N'dfghjkjmhgnfbd', N'fdghjklhgfdsa', CAST(N'2023-11-11T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (37, N'steve', 72, 14, N'?tyjkuewertyj', N'rstdhgsfdrjhxg', CAST(N'2023-12-08T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (38, N'steve', 73, 15, N'ghmgfdssfghj', N'fghjmnhbgvfcdxsdfgbnhmj', CAST(N'2023-12-08T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (39, N'steve', 65, 23, N'fdghjgfdsfghf', N'dsdfhcgjgfdsfdghjg', CAST(N'2023-12-09T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (40, N'tranhiwp', 84, 15, N'cvbgfhmjhnbfvdc', N'cvfdbnm,mnbvc', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (41, N'tranhiwp', 85, 15, N'vgdjbvc', N'fgdhfjhvc', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (42, N'tranhiwp', 81, 16, N'fgnjghfdvsc', N'bnhjnbgfvdc', CAST(N'2024-01-14T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (43, N'tranhiwp', 82, 16, N'dfgdgfbgvc', N'vdghfjgkmjnhbgvdcf', CAST(N'2024-01-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (44, N'tranhiwp', 88, 19, N'kjhdsfbhervgscd', N'bvgrfdbvgd', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (45, N'tranhiwp', 89, 19, N'kntjbhrvgcfxdcv', N',mkuynjthbgvcd', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (46, N'tranhiwp', 90, 19, N'cgvhbjnkmlbvj', N'fcygvhjnbtrfdvs', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (47, N'tranhiwp', 92, 19, N'mfngbdv', N'lkiymjnfbg', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (48, N'tranhiwp', 75, 23, N'hjkhjhbvgcvbnmh,j', N'gfgdhjklkjhgfdd', CAST(N'2023-11-12T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (49, N'steve', 93, 19, N'u?tbvdcl;loikujyth', N'liukyjthrgefcwxcvbn', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (50, N'steve', 99, 19, N'fcgvhbjnkml', N'fcgvhbnherdf', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (51, N'steve', 77, 33, N'hggdgfdhjkjhg', N'sdfghjkhgfdsfghj', CAST(N'2023-12-09T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (52, N'steve', 94, 23, N'v?hnbvgsdf', N'bjrthdvfgcfv', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (53, N'steve', 69, 28, N'easdhfnbvsvbgnh', N'csdvfbhjfvcxdfg', CAST(N'2023-12-09T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (54, N'steve', 76, 28, N'hjkjrtykjhujkjhtgffd', N'djhgfsdjgadsfgh', CAST(N'2023-09-08T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (55, N'steve', 91, 14, N'hebrhbr', N'vrthbbverc', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (56, N'steve', 101, 14, N'jhfgdbvscx', N'mjghfbdvcsx', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (57, N'steve', 105, 15, N'tynjbhrvdgcsf', N'tjynhbrfdvgsc', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (58, N'steve', 106, 15, N'nkjbthdrvgecsf', N'tnjbrhdvgscf', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (59, N'steve', 102, 14, N'hk,jgmfbdvsca', N'lkjghnfbdvsc', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (60, N'steve', 103, 14, N'tctbyvrsca', N'rbthdvgsf', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (61, N'steve', 107, 15, N'vtdhrgsfvcdafs', N'bdtfvhrdcg', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (62, N'tranhiwp', 117, 15, N'ikumynjbthvrgf', N'fvyghbjnkm', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (63, N'tranhiwp', 118, 15, N'fcygvhbnjkm', N'ycvuhbkjnl', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (64, N'tranhiwp', 108, 16, N'vgrscfxdfgh', N'htyrgdcefsx', CAST(N'2024-01-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (65, N'tranhiwp', 109, 16, N'mngjfvdcsx', N'mkynjhbtgvfdc', CAST(N'2024-01-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (66, N'tranhiwp', 110, 16, N'xctygvhjbk', N'dryctvguhjbk', CAST(N'2024-01-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (67, N'tranhiwp', 100, 19, N'xrtcfgvhbjnkm', N'detrfygvhbjnkm', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (70, N'tranhiwp', 104, 14, N'ntjbrhvgecsfa', N'jnyrthbdgvsfc', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (71, N'tranhiwp', 119, 15, N'fndgbsfvac', N'ilukyfdf', CAST(N'2023-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (72, N'tranhiwp', 127, 16, N'dfcgvhbjnkm', N'drfgvhbjnkm', CAST(N'2024-01-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (73, N'tranhiwp', 132, 16, N'fgvhbjnmk', N'fgvyhbjnm', CAST(N'2024-08-30T00:00:00.000' AS DateTime))
INSERT [dbo].[OrdersHistory] ([orderID], [username], [accountID], [productID], [email], [password], [dueDate]) VALUES (74, N'tranhiwp', 193, 15, N'dffnbdvscvsbd', N'bvcxcsdvbfngm,mnb', CAST(N'2023-10-26T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[OrdersHistory] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductList] ON 

INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'quizlet', N'Quizlet Plus 1 month', N'1m', 20000, N'Quizlet is a web tool and a mobile app that boosts students learning through several study tools that include flashcards and game-based quizzes.', N'Quizlet is a web tool and a mobile app that boosts students learning through several study tools that include flashcards and game-based quizzes. You can either design your own study sets from scratch or search for pre-made sets to customize and use in your teaching. Quizlet takes information and converts it into flashcards, quizzes, and games, so that users can study the same information in a variety of forms.  ', 14, N'https://vnpremium.com/assets/img/product/quizlet-plus-1-nam.png', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'netflix', N'Netflix 1 month', N'1m', 50000, N'Netflix is a subscription-based streaming service that allows our members to watch TV shows and movies on an internet-connected device.', N'Netflix is a subscription-based streaming service that allows our members to watch TV shows and movies on an internet-connected device. Depending on your plan, you can also download TV shows and movies to your iOS, Android, or Windows 10 device and watch without an internet connection.', 15, N'https://images.ctfassets.net/4cd45et68cgf/Rx83JoRDMkYNlMC9MKzcB/2b14d5a59fc3937afd3f03191e19502d/Netflix-Symbol.png?w=700&h=456', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'quizlet', N'Quizlet Plus 6 months', N'6m', 100000, N'Quizlet is a web tool and a mobile app that boosts students learning through several study tools that include flashcards and game-based quizzes.', N'Quizlet is a web tool and a mobile app that boosts students learning through several study tools that include flashcards and game-based quizzes. You can either design your own study sets from scratch or search for pre-made sets to customize and use in your teaching. Quizlet takes information and converts it into flashcards, quizzes, and games, so that users can study the same information in a variety of forms.  ', 16, N'https://assets.quizlet.com/a/j/dist/app/i/brandmark/1024.0e9431247202b7b.png', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'youtube', N'Youtube Premium 1 month', N'1m', 10000, N'YouTube is an American online video sharing and social media platform headquartered in San Bruno, California, United States.', N'YouTube is an American online video sharing and social media platform headquartered in San Bruno, California, United States. Accessible worldwide, it was launched on February 14, 2005, by Steve Chen, Chad Hurley, and Jawed Karim. It is owned by Google and is the second most visited website, after Google Search.', 19, N'https://play-lh.googleusercontent.com/lMoItBgdPPVDJsNOVtP26EKHePkwBg-PkuY9NOrc-fumRtTFP4XhpUNk_22syN4Datc', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'canva', N'Canva Pro 1 month', N'1m', 50000, N'Canva Pro - Best Design', N'Canva Pro', 23, N'https://freelogopng.com/images/all_img/1656733637logo-canva-png.png', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'canva', N'Canva Pro 6 months', N'6m', 300000, N'Canva Pro - Best Design', N'Canva Pro', 24, N'https://freelogopng.com/images/all_img/1656733637logo-canva-png.png', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'netflix', N'Netflix 6 months', N'6m', 500000, N'Netflix is a subscription-based streaming service that allows our members to watch TV shows and movies on an internet-connected device.', N'Netflix is a subscription-based streaming service that allows our members to watch TV shows and movies on an internet-connected device. Depending on your plan, you can also download TV shows and movies to your iOS, Android, or Windows 10 device and watch without an internet connection.', 25, N'https://images.ctfassets.net/4cd45et68cgf/Rx83JoRDMkYNlMC9MKzcB/2b14d5a59fc3937afd3f03191e19502d/Netflix-Symbol.png?w=700&h=456', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'netflix', N'Netflix 1 year', N'1y', 5000000, N'Netflix is a subscription-based streaming service that allows our members to watch TV shows and movies on an internet-connected device.', N'Netflix is a subscription-based streaming service that allows our members to watch TV shows and movies on an internet-connected device. Depending on your plan, you can also download TV shows and movies to your iOS, Android, or Windows 10 device and watch without an internet connection.', 26, N'https://images.ctfassets.net/4cd45et68cgf/Rx83JoRDMkYNlMC9MKzcB/2b14d5a59fc3937afd3f03191e19502d/Netflix-Symbol.png?w=700&h=456', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'gmail', N'Gmail 1 month', N'1m', 50000, N'Gmail is a free email service provided by Google.', N'Gmail is a free email service provided by Google. In many ways, Gmail is like any other email service: You can send and receive emails, block spam, create an address book, and perform other basic email tasks. But it also has some more unique features that help make it one of the most popular online email services.', 28, N'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Gmail_icon_%282020%29.svg/1024px-Gmail_icon_%282020%29.svg.png', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'quizlet', N'Quizlet 3 months', N'3m', 2500000, N'Quizlet is a web tool and a mobile app that boosts students learning through several study tools that include flashcards and game-based quizzes.', N'Quizlet is a web tool and a mobile app that boosts students learning through several study tools that include flashcards and game-based quizzes. You can either design your own study sets from scratch or search for pre-made sets to customize and use in your teaching. Quizlet takes information and converts it into flashcards, quizzes, and games, so that users can study the same information in a variety of forms.  ', 29, N'https://assets.quizlet.com/a/j/dist/app/i/brandmark/1024.0e9431247202b7b.png', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'canva', N'Canva 1 year', N'1y', 5000000, N'Canva is a graphic designing tool that is very easy to use & helps in creating amazing designs quickly.', N'Canva is a free design platform with countless templates and ideas to work from. You can make almost anything from slides for your presentation to infographics/flyers to instagram posts. It’s user-friendly interface makes it ideal for beginners or people who just need something simple', 30, N'https://freelogopng.com/images/all_img/1656733637logo-canva-png.png', 0)
INSERT [dbo].[ProductList] ([catalogID], [title], [type], [price], [shortDetail], [longDetail], [productID], [productImg], [quantity]) VALUES (N'chatgpt', N'ChatGPT Plus 1 month', N'1m', 50000, N'ChatGPT is a chatbot application powered by the GPT-3.5 and GPT-4 architecture.', N'It is designed to provide human-like conversation experiences by processing natural language inputs and generating relevant responses. ChatGPT is a web-based platform that can be accessed via any web browser.', 33, N'https://shsroundtable.com/wp-content/uploads/2023/01/chat-gpt-logo.jpg', 0)
SET IDENTITY_INSERT [dbo].[ProductList] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductRate] ON 

INSERT [dbo].[ProductRate] ([rateID], [username], [productID], [star], [comment], [rateDate]) VALUES (10, N'tranhiwp', 25, 5, N'Uy tin so 1 Viet Nam', CAST(N'2023-07-13T07:57:29.443' AS DateTime))
INSERT [dbo].[ProductRate] ([rateID], [username], [productID], [star], [comment], [rateDate]) VALUES (13, N'tranhiwp', 14, 5, N'Uy tin khong can ban!', CAST(N'2023-07-14T17:28:05.923' AS DateTime))
INSERT [dbo].[ProductRate] ([rateID], [username], [productID], [star], [comment], [rateDate]) VALUES (14, N'steve', 15, 2, N'Tạm ổn', CAST(N'2023-07-14T22:06:06.697' AS DateTime))
INSERT [dbo].[ProductRate] ([rateID], [username], [productID], [star], [comment], [rateDate]) VALUES (16, N'steve', 14, 1, N'Lua dao!', CAST(N'2023-07-16T23:26:45.380' AS DateTime))
INSERT [dbo].[ProductRate] ([rateID], [username], [productID], [star], [comment], [rateDate]) VALUES (17, N'steve', 33, 5, N'10 diem khong co nhung!', CAST(N'2023-07-17T10:23:57.967' AS DateTime))
INSERT [dbo].[ProductRate] ([rateID], [username], [productID], [star], [comment], [rateDate]) VALUES (21, N'tranhiwp', 19, 5, N'Uy tin so 2 Viet Nam', CAST(N'2023-09-21T16:07:10.523' AS DateTime))
INSERT [dbo].[ProductRate] ([rateID], [username], [productID], [star], [comment], [rateDate]) VALUES (1021, N'tranhiwp', 15, 1, N'Shop dau buoi', CAST(N'2023-10-09T16:38:36.507' AS DateTime))
SET IDENTITY_INSERT [dbo].[ProductRate] OFF
GO
INSERT [dbo].[RoleInformation] ([roleID], [roleName]) VALUES (0, N'User')
INSERT [dbo].[RoleInformation] ([roleID], [roleName]) VALUES (1, N'Admin')
INSERT [dbo].[RoleInformation] ([roleID], [roleName]) VALUES (2, N'Stock Managerment')
INSERT [dbo].[RoleInformation] ([roleID], [roleName]) VALUES (3, N'Accountant')
GO
INSERT [dbo].[UserBalance] ([username], [balance]) VALUES (N'admin', 200000)
INSERT [dbo].[UserBalance] ([username], [balance]) VALUES (N'hieptvhe173252', 100000)
INSERT [dbo].[UserBalance] ([username], [balance]) VALUES (N'hiwphiwphiwp', 0)
INSERT [dbo].[UserBalance] ([username], [balance]) VALUES (N'steve', 1700000)
INSERT [dbo].[UserBalance] ([username], [balance]) VALUES (N'tranhiwp', 1690000)
INSERT [dbo].[UserBalance] ([username], [balance]) VALUES (N'tranhiwp1111', 0)
INSERT [dbo].[UserBalance] ([username], [balance]) VALUES (N'tranhiwpppp', 0)
GO
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'admin', N'admin', N'Admin', N'hieptvhe173252@gmail.com', 1, 1)
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'admin2', N'admin2', N'Stock Manager', N'hieptran.pa@gmail.com', 2, 1)
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'admin3', N'admin3@', N'Accountant', N'hieptran.pa@gmail.com', 3, 1)
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'hieptvhe173252', N'123', N'Tran Van Hiep (K17 HL)', N'hieptvhe173252@fpt.edu.vn', 3, 1)
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'hiwphiwphiwp', N'123', N'Hiwp Hiwp', N'hieptran.pa@gmail.com', 0, 1)
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'steve', N'steve', N'Steve', N'datnghia.pa@gmail.com', 0, 1)
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'tranhiwp', N'123', N'Tran Van Hiep K17 HL', N'hieptran.pa@gmail.com', 0, 1)
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'tranhiwp1111', N'123', N'Tran Hiep', N'hieptran.pa@gmail.com', 0, 0)
INSERT [dbo].[Users] ([username], [password], [fullname], [email], [role], [isVerificated]) VALUES (N'tranhiwpppp', N'123', N'Tran Hiep', N'datnghia.pa@gmail.com', 0, 0)
GO
ALTER TABLE [dbo].[AccountList]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[ProductList] ([productID])
GO
ALTER TABLE [dbo].[BalanceHistory]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[ProductList] ([productID])
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[DepositHistory]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[DepositRequest]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[OrdersHistory]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[ProductList] ([productID])
GO
ALTER TABLE [dbo].[OrdersHistory]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[ProductList]  WITH CHECK ADD FOREIGN KEY([catalogID])
REFERENCES [dbo].[CatalogList] ([catalogID])
GO
ALTER TABLE [dbo].[ProductRate]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[ProductList] ([productID])
GO
ALTER TABLE [dbo].[ProductRate]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[UserBalance]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD FOREIGN KEY([role])
REFERENCES [dbo].[RoleInformation] ([roleID])
GO
USE [master]
GO
ALTER DATABASE [AccountShopDB] SET  READ_WRITE 
GO
