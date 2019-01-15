package br.com.relatorio.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.relatorio.util.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class GeradorDeRelatorios {
	
	private Connection conexao;
	private Map<String, Object> parametros;
	private String nomeArquivo;

	public GeradorDeRelatorios(String nomeArquivo, Connection conexao, Map<String, Object> parametros) {
		this.nomeArquivo = nomeArquivo;
		this.conexao = conexao;
		this.parametros = parametros;
	}
	
	public void geraRelatorios(OutputStream saida) {
		try {
			//JasperCompileManager.compileReportToFile("jasper/relatorio_movimentacoes.jrxml");

			// carrega o relatorio e parametros - efetuar tratamento de erros (refactor)
			JasperPrint jasperPrint = JasperFillManager.fillReport(this.nomeArquivo, this.parametros,
					this.conexao);

			// exporta o relatorio
			JRExporter exportaPDF = new JRPdfExporter();
			exportaPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exportaPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);

			exportaPDF.exportReport();

			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}

}
