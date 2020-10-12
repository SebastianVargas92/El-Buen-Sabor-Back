package com.example.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.entitie.ArticuloInsumo;
import com.example.entitie.ArticuloReventa;
import com.example.entitie.DetallePedido;
import com.example.entitie.Pedido;
import com.example.entitie.PlatoVendido;

public class GenerateExcel {

	public static ByteArrayInputStream pedidoClienteExcel(List<Pedido> pedidos, String desde, String hasta)
			throws IOException {
		String[] COLUMNs = { "Fecha", "Estado", "Detalle", "cantidad" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("PedidosPorCliente");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRowUno = sheet.createRow(0);
			headerRowUno.createCell(0).setCellValue("Cliente");
			headerRowUno.createCell(1).setCellValue(pedidos.get(0).getCliente().getNombre());
			headerRowUno.createCell(2).setCellValue(pedidos.get(0).getCliente().getApellido());

			Row headerRowDos = sheet.createRow(2);
			headerRowDos.createCell(0).setCellValue("Fecha");

			Row headerRowTres = sheet.createRow(2);
			headerRowTres.createCell(0).setCellValue("Desde");
			headerRowTres.createCell(1).setCellValue(formatoFecha(desde));
			headerRowTres.createCell(3).setCellValue("Hasta");
			headerRowTres.createCell(4).setCellValue(formatoFecha(hasta));

			// Row for Header
			Row headerRow = sheet.createRow(4);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 5;
			for (Pedido customer : pedidos) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(customer.getFecha().toString());
				if (customer.getEstado() == 0) {
					row.createCell(1).setCellValue("Cancelado");
				} else if (customer.getEstado() == 1) {
					row.createCell(1).setCellValue("A confirmar");
				} else if (customer.getEstado() == 2) {
					row.createCell(1).setCellValue("En cocina");
				} else if (customer.getEstado() == 3) {
					row.createCell(1).setCellValue("Listo");
				} else if (customer.getEstado() == 4) {
					row.createCell(1).setCellValue("En delivery");
				} else if (customer.getEstado() == 5) {
					row.createCell(1).setCellValue("Facturado");
				}
				String detalles = "";
				String cant = "";
				for (DetallePedido dp : customer.getDetallePedido()) {

					if (dp.getArticuloManufacturado() != null) {
						detalles += dp.getArticuloManufacturado().getDenominacion() + "\n";
						cant += dp.getCantidad() + "\n";
					} else {
						detalles += dp.getArticuloReventa().getDenominacion() + "\n";
						cant += dp.getCantidad() + "\n";
					}
				}
				row.createCell(2).setCellValue(detalles);

				row.createCell(3).setCellValue(cant);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	
	
	public static ByteArrayInputStream sinStockExcel(List<ArticuloInsumo>insumos,List<ArticuloReventa>revetas )
			throws IOException {
		
		
		String[] COLUMNs = { "ID", "Denominacion", "Stock Minimo", "Stock Actual" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("SinStock");
			

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRowUno = sheet.createRow(0);
			Cell cel = headerRowUno.createCell(0);
			cel.setCellValue("Insumos con stock minimo");
			cel.setCellStyle(headerCellStyle);


			// Row for Header
			Row headerRow = sheet.createRow(2);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 3;
			for (ArticuloInsumo insumo : insumos) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(insumo.getId());
				
				row.createCell(1).setCellValue(insumo.getDenominacion());
				
				row.createCell(2).setCellValue(insumo.getStockMinimo());
				
				row.createCell(3).setCellValue(insumo.getUnidadMedida().getDenominacion());

				row.createCell(4).setCellValue(insumo.getStockActual());
				
				row.createCell(5).setCellValue(insumo.getUnidadMedida().getDenominacion());
			}
			
			Row headerRowDos = sheet.createRow(rowIdx++ +1);
			Cell celDos = headerRowDos.createCell(0);
			celDos.setCellValue("Reventa con stock Minimo");
			celDos.setCellStyle(headerCellStyle);
			
			Row headerRowD = sheet.createRow(rowIdx++);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRowD.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			for (ArticuloReventa reventa : revetas) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(reventa.getId());
				
				row.createCell(1).setCellValue(reventa.getDenominacion());
				
				row.createCell(2).setCellValue(reventa.getStockMinimo());

				row.createCell(3).setCellValue(reventa.getStockActual());
			}

			workbook.write(out);
			
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	
	public static ByteArrayInputStream masPedidasExcel(List<PlatoVendido> platos, String desde, String hasta)
			throws IOException {
		String[] COLUMNs = { "User alta", "Denominacion", "precio", "cantidad" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("PedidosPorCliente");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);


			Row headerRowDos = sheet.createRow(0);
			headerRowDos.createCell(0).setCellValue("Fecha");

			Row headerRowTres = sheet.createRow(1);
			headerRowTres.createCell(0).setCellValue("Desde");
			headerRowTres.createCell(1).setCellValue(formatoFecha(desde));
			headerRowTres.createCell(3).setCellValue("Hasta");
			headerRowTres.createCell(4).setCellValue(formatoFecha(hasta));

			// Row for Header
			Row headerRow = sheet.createRow(4);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 5;
			for (PlatoVendido plato : platos) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(plato.getArticulo().getUsuarioCarga());
				row.createCell(1).setCellValue(plato.getArticulo().getDenominacion());
				row.createCell(2).setCellValue(plato.getArticulo().getPrecioVenta());
				row.createCell(3).setCellValue(plato.getCantidad());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	public static ByteArrayInputStream ingresosExcel(List<Pedido> pedidos, String desde, String hasta)
			throws IOException {
		String[] COLUMNs = { "Fecha", "Cliente", "Total", "Descuento" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("PedidosPorCliente");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRowUno = sheet.createRow(0);
			headerRowUno.createCell(0).setCellValue("Ingresos Generados");
			
			Row headerRowU = sheet.createRow(1);
			Cell cel = headerRowU.createCell(0);
			cel.setCellValue("Total facturado");
			cel.setCellStyle(headerCellStyle);
			
			double total = calcularTotal(pedidos);
			
			headerRowU.createCell(1).setCellValue(total);
			
			
			Row headerRowDos = sheet.createRow(2);
			headerRowDos.createCell(0).setCellValue("Fecha");

			Row headerRowTres = sheet.createRow(2);
			headerRowTres.createCell(0).setCellValue("Desde");
			headerRowTres.createCell(1).setCellValue(formatoFecha(desde));
			headerRowTres.createCell(3).setCellValue("Hasta");
			headerRowTres.createCell(4).setCellValue(formatoFecha(hasta));

			// Row for Header
			Row headerRow = sheet.createRow(4);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 5;
			for (Pedido p : pedidos) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getFecha().toString());
				row.createCell(1).setCellValue(p.getCliente().getCorreo());
				row.createCell(2).setCellValue(p.getFactura().getTotal());
				row.createCell(3).setCellValue(p.getFactura().getMontoDescuento());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	
	public static double calcularTotal(List<Pedido> pedidos) {
		double total =0;
		for(Pedido p : pedidos) {
			
			total+= p.getFactura().getTotal();
			
		}
		
		return total;
	}
	
	public static String formatoFecha(String fecha) {
		 String[] parts =fecha.split("-");
		 String devuelve = "";
		 for(int i = parts.length-1;i>=0;i--) {
			 devuelve += parts[i].trim();
			 if(i != 0 ) {
				 devuelve += "/";
			 }
		 }
		return devuelve;
	}

}
