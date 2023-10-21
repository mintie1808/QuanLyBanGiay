USE [Sql_web_ban__quan_ao]
GO
/****** Object:  Table [dbo].[Category_tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category_tb](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[category_name] [nchar](100) NULL,
 CONSTRAINT [PK_Category_tb] PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer__tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer__tb](
	[CustomerID] [int] NOT NULL,
	[CustomerName] [nchar](100) NULL,
 CONSTRAINT [PK_Customer__tb] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice__tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice__tb](
	[InvoiceID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NULL,
	[Status] [nvarchar](50) NULL,
 CONSTRAINT [PK_Invoic1__tb] PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order__tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order__tb](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NULL,
	[OrderDate] [datetime] NULL,
	[TotalAmount] [int] NULL,
 CONSTRAINT [PK_Order__tb] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail__tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail__tb](
	[OrderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NULL,
	[ProductID] [int] NULL,
	[Quantity] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product__tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product__tb](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](100) NULL,
	[Price] [int] NULL,
	[Color] [nvarchar](50) NULL,
	[SupplierID] [int] NULL,
	[Type] [int] NULL,
 CONSTRAINT [PK_Produc1__tb] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Seller__tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Seller__tb](
	[SellerID] [int] NOT NULL,
	[SellerName] [nchar](100) NULL,
	[TotalSales] [nchar](100) NULL,
 CONSTRAINT [PK_Seller__tb] PRIMARY KEY CLUSTERED 
(
	[SellerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supplier__tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supplier__tb](
	[SupplierID] [int] IDENTITY(1,1) NOT NULL,
	[SupplierName] [nvarchar](100) NULL,
	[Address] [nvarchar](100) NULL,
	[ProductsSupplied] [nvarchar](50) NULL,
	[PaymentTerms] [nvarchar](50) NULL,
 CONSTRAINT [PK_Supplier__tb] PRIMARY KEY CLUSTERED 
(
	[SupplierID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User__tb]    Script Date: 21/10/2023 7:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User__tb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nchar](200) NULL,
	[Password] [nchar](100) NULL,
	[Role] [nchar](10) NULL,
	[Email] [nchar](100) NULL,
	[Phone] [int] NULL,
	[Address] [nchar](100) NULL,
 CONSTRAINT [PK_User__tb] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Invoice__tb]  WITH CHECK ADD  CONSTRAINT [FK_Invoice__tb_Order__tb] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order__tb] ([OrderID])
GO
ALTER TABLE [dbo].[Invoice__tb] CHECK CONSTRAINT [FK_Invoice__tb_Order__tb]
GO
ALTER TABLE [dbo].[Order__tb]  WITH CHECK ADD  CONSTRAINT [FK_Order__tb_Customer__tb] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer__tb] ([CustomerID])
GO
ALTER TABLE [dbo].[Order__tb] CHECK CONSTRAINT [FK_Order__tb_Customer__tb]
GO
ALTER TABLE [dbo].[Order__tb]  WITH CHECK ADD  CONSTRAINT [FK_Order__tb_Customer__tb1] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer__tb] ([CustomerID])
GO
ALTER TABLE [dbo].[Order__tb] CHECK CONSTRAINT [FK_Order__tb_Customer__tb1]
GO
ALTER TABLE [dbo].[OrderDetail__tb]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail__tb_Order__tb] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order__tb] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail__tb] CHECK CONSTRAINT [FK_OrderDetail__tb_Order__tb]
GO
ALTER TABLE [dbo].[OrderDetail__tb]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail__tb_Product__tb] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product__tb] ([ProductID])
GO
ALTER TABLE [dbo].[OrderDetail__tb] CHECK CONSTRAINT [FK_OrderDetail__tb_Product__tb]
GO
ALTER TABLE [dbo].[Product__tb]  WITH CHECK ADD  CONSTRAINT [FK_Product__tb_Category_tb] FOREIGN KEY([Type])
REFERENCES [dbo].[Category_tb] ([category_id])
GO
ALTER TABLE [dbo].[Product__tb] CHECK CONSTRAINT [FK_Product__tb_Category_tb]
GO
ALTER TABLE [dbo].[Product__tb]  WITH CHECK ADD  CONSTRAINT [FK_Product__tb_Supplier__tb] FOREIGN KEY([SupplierID])
REFERENCES [dbo].[Supplier__tb] ([SupplierID])
GO
ALTER TABLE [dbo].[Product__tb] CHECK CONSTRAINT [FK_Product__tb_Supplier__tb]
GO
ALTER TABLE [dbo].[User__tb]  WITH CHECK ADD  CONSTRAINT [FK_User__tb_Customer__tb] FOREIGN KEY([id])
REFERENCES [dbo].[Customer__tb] ([CustomerID])
GO
ALTER TABLE [dbo].[User__tb] CHECK CONSTRAINT [FK_User__tb_Customer__tb]
GO
ALTER TABLE [dbo].[User__tb]  WITH CHECK ADD  CONSTRAINT [FK_User__tb_Seller__tb] FOREIGN KEY([id])
REFERENCES [dbo].[Seller__tb] ([SellerID])
GO
ALTER TABLE [dbo].[User__tb] CHECK CONSTRAINT [FK_User__tb_Seller__tb]
GO
