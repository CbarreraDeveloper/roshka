package com.test.roshka.services;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import com.test.roshka.bean.Noticia;
import com.test.roshka.error.CustomInvalidQueryParametersException;
import com.test.roshka.error.CustomNotFoundException;
import com.test.roshka.utilitarios.Constantes;
import com.test.roshka.utilitarios.NoticiasUtilis;

@Service
public class NoticiaServicesImpl implements NoticiasService {

	@Override
	public ResponseEntity<?> getObtenerNoticia(String textoBuscar) throws Exception {

		if (textoBuscar.isEmpty() || NoticiasUtilis.isNullOrEmpty(textoBuscar)) {
			throw new CustomInvalidQueryParametersException();
		}

		String url = Constantes.URL_ABC + textoBuscar;
		ArrayList<Noticia> noticiaList = new ArrayList<>();

		Playwright playwright = Playwright.create();

		final BrowserType chromium = playwright.chromium();
		final Browser browser = chromium.launch();
		Page page = browser.newPage();
		page.navigate(url);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		final ElementHandle contentElement = page.querySelector("[class=article-list-wrapper]");

		Document document = null;
		if (contentElement == null) {
			throw new CustomNotFoundException(textoBuscar);
		}

		document = Jsoup.parse(contentElement.innerHTML());
		Elements articulos = document.select("div.item-article");

		String fecha = "";
		String enlace = "";
		String enlaceFoto = "";
		String titulo = "";
		String resumen = "";

		for (Element articulo : articulos) {

			Noticia noticia = new Noticia();

			fecha = articulo.selectFirst("div.article-info").selectFirst("div.article-time").selectFirst("span").text();
			System.out.println(fecha);
			noticia.setFecha(NoticiasUtilis.getFechaFormatoIso(fecha));

			enlace = articulo.selectFirst("div.article-info").selectFirst("a").attr("href");
			noticia.setEnlace(enlace);

			enlaceFoto = articulo.selectFirst("div.article-photo").selectFirst("img").attr("src");
			noticia.setEnlaceFoto(enlaceFoto);

			titulo = articulo.selectFirst("div.article-info").selectFirst("div.article-title").selectFirst("span")
					.text();
			noticia.setTitulo(titulo);

			resumen = articulo.selectFirst("div.article-info").selectFirst("div.article-intro").selectFirst("p").text();
			noticia.setResumen(resumen);

			noticiaList.add(noticia);
		}

		browser.close();

		return new ResponseEntity<>(noticiaList, HttpStatus.OK);
	}

}
