package br.com.relatorio.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.relatorio.controller.GeradorDeRelatorios;
import br.com.relatorio.util.ConnectionFactory;

@WebServlet(urlPatterns = "/servletRelatorio")
public class ServletRelatorio extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Connection conexao = new ConnectionFactory().getConnection();

			String paramDataInicio = req.getParameter("data_ini");
			String paramDataFim = req.getParameter("data_fim");

			Date dataInicio = converteStringParaData(paramDataInicio);
			Date dataFim = converteStringParaData(paramDataFim);

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("DATA_INI", dataInicio);
			parametros.put("DATA_FIM", dataFim);

			String nomeArquivo = req.getRealPath("/WEB-INF/jasper/relatorio_movimentacoes.jasper");
			GeradorDeRelatorios geradorDeRelatorios = new GeradorDeRelatorios(nomeArquivo, conexao, parametros);
			OutputStream arquivo = resp.getOutputStream();
			
			geradorDeRelatorios.geraRelatorios(arquivo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Date converteStringParaData(String data) {
		Date novaData = new Date();
		if(data != null) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				novaData = format.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return novaData;
	}
}
