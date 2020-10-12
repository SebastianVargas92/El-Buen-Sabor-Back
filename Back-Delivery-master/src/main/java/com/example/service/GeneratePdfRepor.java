package com.example.service;

import com.example.entitie.DetallePedido;
import com.example.entitie.Pedido;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class GeneratePdfRepor {

	public static ByteArrayInputStream citiesReport(Pedido p) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			Paragraph par1 = new Paragraph();
			Font fonttitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);

			par1.add(new Phrase("Factura El Buen Sabor", fonttitulo));
			par1.setAlignment(Element.ALIGN_CENTER);
			par1.add(new Phrase(Chunk.NEWLINE));
			par1.add(new Phrase(Chunk.NEWLINE));

			Paragraph par2 = new Paragraph();
			Font fontt = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			par2.add(new Phrase("Numero :         " + p.getFactura().getNumero() + "\nFecha :           "
					+ p.getFactura().getFecha() + "\nCliente      :  " + p.getCliente().getNombre() + "  "
					+ p.getCliente().getApellido() + " \nDomicilio Cliente :      "
					+ p.getCliente().getDomicilio().getCalle() + "  " + p.getCliente().getDomicilio().getNumero()
					+ "\nDetalleFactura : ", fontt));
			par2.setAlignment(Element.ALIGN_LEFT);

			par2.add(new Phrase(Chunk.NEWLINE));
			par2.add(new Phrase(Chunk.NEWLINE));

			PdfPTable table = new PdfPTable(4);
			table.addCell("Articulo Denominación");
			table.addCell("Precio");
			table.addCell("Cantidad");
			table.addCell("Subtotal");

			for (DetallePedido dp : p.getDetallePedido()) {
				if (dp.getArticuloManufacturado() != null) {
					table.addCell(dp.getArticuloManufacturado().getDenominacion());
					table.addCell(String.valueOf(dp.getArticuloManufacturado().getPrecioVenta()));
				} else {
					table.addCell(dp.getArticuloReventa().getDenominacion());
					table.addCell(String.valueOf(dp.getArticuloReventa().getPrecioVenta()));
				}
				table.addCell(String.valueOf(dp.getCantidad()));
				table.addCell(String.valueOf(dp.getSubtotal()));

			}

			Paragraph par3 = new Paragraph();
			par3.add(new Phrase("Descuento :   " + p.getFactura().getMontoDescuento(), fontt));
			par3.setAlignment(Element.ALIGN_LEFT);

			Paragraph par4 = new Paragraph();
			par4.add(new Phrase("Total :   " + p.getFactura().getTotal(), fontt));
			par4.setAlignment(Element.ALIGN_LEFT);

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(par1);
			document.add(par2);
			document.add(table);
			document.add(par3);
			document.add(par4);
			
			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
