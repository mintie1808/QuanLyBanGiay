USE [Sql_web_ban__quan_ao]
GO
/****** Object:  Table [dbo].[Blog__tb]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Blog__tb](
	[IDblog] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](255) NULL,
	[Detail] [nvarchar](max) NULL,
	[Description] [nvarchar](max) NULL,
	[img] [nvarchar](255) NULL,
	[CreatedDate] [datetime] NULL,
	[SeoTitle] [nvarchar](255) NULL,
	[UserID] [int] NULL,
 CONSTRAINT [PK_Blog__tb] PRIMARY KEY CLUSTERED 
(
	[IDblog] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category_tb]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category_tb](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[Seotitle] [nvarchar](100) NULL,
	[category_name] [nvarchar](100) NULL,
	[CreatedDate] [datetime] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Cate ry_tb] PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice__tb]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice__tb](
	[InvoiceID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NULL,
	[Status] [nvarchar](50) NULL,
	[InvoiceDate] [datetime] NULL,
 CONSTRAINT [PK_Invoic1__tb] PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order__tb]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order__tb](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NULL,
	[OrderDate] [datetime] NULL,
	[TotalAmount] [int] NULL,
	[status] [nvarchar](255) NULL,
 CONSTRAINT [PK_Order__tb] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail__tb]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail__tb](
	[OrderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NULL,
	[ProductID] [int] NULL,
	[Quantity] [int] NULL,
	[PaymentMethod] [nvarchar](255) NULL,
 CONSTRAINT [PK_OrderDetail__tb] PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product__tb]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product__tb](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](100) NULL,
	[Price] [int] NULL,
	[Seotitle] [nvarchar](100) NULL,
	[Color] [nvarchar](50) NULL,
	[SupplierID] [int] NULL,
	[category_id] [int] NULL,
	[Status] [bit] NULL,
	[Description] [nvarchar](50) NULL,
	[Detail] [ntext] NULL,
	[Img] [nvarchar](255) NULL,
	[Amount] [int] NULL,
	[Sold] [int] NULL,
	[Img2] [nvarchar](255) NULL,
	[Hot] [bit] NULL,
	[SALE] [bit] NULL,
 CONSTRAINT [PK_Produc1__tb] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role__td]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role__td](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[Code] [nvarchar](255) NULL,
	[Name] [nvarchar](255) NULL,
 CONSTRAINT [PK_Role__td] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SaleStats __tb]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SaleStats __tb](
	[Sale_id] [int] IDENTITY(1,1) NOT NULL,
	[User_id] [int] NULL,
	[Product_id] [int] NULL,
	[Sale_date] [date] NULL,
	[Quantity_sold] [int] NULL,
 CONSTRAINT [PK_SaleStats __tb] PRIMARY KEY CLUSTERED 
(
	[Sale_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supplier__tb]    Script Date: 26/05/2024 2:13:54 CH ******/
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
/****** Object:  Table [dbo].[User__tb]    Script Date: 26/05/2024 2:13:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User__tb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](100) NULL,
	[Password] [nvarchar](100) NULL,
	[Email] [nvarchar](100) NULL,
	[Role] [int] NULL,
	[Fullname] [nvarchar](100) NULL,
	[Address] [nvarchar](100) NULL,
	[Phone] [int] NULL,
	[img] [nvarchar](255) NULL,
	[status] [nvarchar](255) NULL,
 CONSTRAINT [PK_User__tb] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Blog__tb]  WITH CHECK ADD  CONSTRAINT [FK_Blog__tb_User__tb] FOREIGN KEY([UserID])
REFERENCES [dbo].[User__tb] ([id])
GO
ALTER TABLE [dbo].[Blog__tb] CHECK CONSTRAINT [FK_Blog__tb_User__tb]
GO
ALTER TABLE [dbo].[Invoice__tb]  WITH CHECK ADD  CONSTRAINT [FK_Invoice__tb_Order__tb] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order__tb] ([OrderID])
GO
ALTER TABLE [dbo].[Invoice__tb] CHECK CONSTRAINT [FK_Invoice__tb_Order__tb]
GO
ALTER TABLE [dbo].[Order__tb]  WITH CHECK ADD  CONSTRAINT [FK_Order__tb_User__tb] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[User__tb] ([id])
GO
ALTER TABLE [dbo].[Order__tb] CHECK CONSTRAINT [FK_Order__tb_User__tb]
GO
ALTER TABLE [dbo].[OrderDetail__tb]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail__tb_Order__tb] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order__tb] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail__tb] CHECK CONSTRAINT [FK_OrderDetail__tb_Order__tb]
GO
ALTER TABLE [dbo].[OrderDetail__tb]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail__tb_Product__tb1] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product__tb] ([ProductID])
GO
ALTER TABLE [dbo].[OrderDetail__tb] CHECK CONSTRAINT [FK_OrderDetail__tb_Product__tb1]
GO
ALTER TABLE [dbo].[Product__tb]  WITH CHECK ADD  CONSTRAINT [FK_Product__tb_Category_tb] FOREIGN KEY([category_id])
REFERENCES [dbo].[Category_tb] ([category_id])
GO
ALTER TABLE [dbo].[Product__tb] CHECK CONSTRAINT [FK_Product__tb_Category_tb]
GO
ALTER TABLE [dbo].[Product__tb]  WITH CHECK ADD  CONSTRAINT [FK_Product__tb_Supplier__tb] FOREIGN KEY([SupplierID])
REFERENCES [dbo].[Supplier__tb] ([SupplierID])
GO
ALTER TABLE [dbo].[Product__tb] CHECK CONSTRAINT [FK_Product__tb_Supplier__tb]
GO
ALTER TABLE [dbo].[SaleStats __tb]  WITH CHECK ADD  CONSTRAINT [FK_SaleStats __tb_Product__tb1] FOREIGN KEY([Product_id])
REFERENCES [dbo].[Product__tb] ([ProductID])
GO
ALTER TABLE [dbo].[SaleStats __tb] CHECK CONSTRAINT [FK_SaleStats __tb_Product__tb1]
GO
ALTER TABLE [dbo].[SaleStats __tb]  WITH CHECK ADD  CONSTRAINT [FK_SaleStats __tb_User__tb] FOREIGN KEY([User_id])
REFERENCES [dbo].[User__tb] ([id])
GO
ALTER TABLE [dbo].[SaleStats __tb] CHECK CONSTRAINT [FK_SaleStats __tb_User__tb]
GO
ALTER TABLE [dbo].[User__tb]  WITH CHECK ADD  CONSTRAINT [FK_User__tb_Role__td] FOREIGN KEY([Role])
REFERENCES [dbo].[Role__td] ([RoleID])
GO
ALTER TABLE [dbo].[User__tb] CHECK CONSTRAINT [FK_User__tb_Role__td]
GO
