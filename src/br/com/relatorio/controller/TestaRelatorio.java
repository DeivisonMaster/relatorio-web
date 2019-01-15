package br.com.relatorio.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.relatorio.util.ConnectionFactory;

public class TestaRelatorio {
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		Connection conexao = new ConnectionFactory().getConnection();
		
		String data_ini = "01/11/2018";
		String data_fim = "30/11/2018";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicial = simpleDateFormat.parse(data_ini);
		Date dataFim = simpleDateFormat.parse(data_fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DATA_INI", dataInicial);
		parametros.put("DATA_FIM", dataFim);
		
		
		String nomeArquivoGerado = "movimentacoes.pdf";
		String diretorio   = "pdf/";
		
		String nomeArquivo = "jasper/relatorio_movimentacoes.jasper";
		GeradorDeRelatorios geradorDeRelatorios = new GeradorDeRelatorios(nomeArquivo, conexao, parametros);
		OutputStream arquivo = new FileOutputStream(diretorio + nomeArquivoGerado);
		geradorDeRelatorios.geraRelatorios(arquivo);
		
	}
}
