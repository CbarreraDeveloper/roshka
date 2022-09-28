package com.test.roshka.services;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import com.test.roshka.bean.Noticia;
import com.test.roshka.error.CustomInternalServerErrorException;
import com.test.roshka.utilitarios.Constantes;
import com.test.roshka.utilitarios.NoticiasUtilis;

@Service
public class NoticiaServicesImpl implements NoticiasService {

	@Override
	public Noticia[] getObtenerNoticia(String textoBuscar) {
		String url =  Constantes.URL_ABC+ textoBuscar;
		ArrayList<Noticia> noticiaList = new ArrayList<>();

		Noticia noticia = null;

		try (Playwright playwright = Playwright.create()) {
			final BrowserType chromium = playwright.chromium();
			final Browser browser = chromium.launch();
			Page page = browser.newPage();
			page.navigate(url);
			page.waitForLoadState(LoadState.NETWORKIDLE);
			final ElementHandle contentElement = page.querySelector("[class=article-list-wrapper]");

			Document document = null;
			if (contentElement == null) {
				noticiaList.toArray(Noticia[]::new);
			}
			document = Jsoup.parse(contentElement.innerHTML());
			Elements articulos = document.select("div.item-article");

			String fecha = "";
			String enlace = "";
			String enlaceFoto = "";
			String titulo = "";
			String resumen = "";

			for (Element articulo : articulos) {

				noticia = new Noticia();

				fecha = articulo.selectFirst("div.article-info").selectFirst("div.article-time").selectFirst("span")
						.text();
				System.out.println(fecha);
				noticia.setFecha(NoticiasUtilis.getFechaFormatoIso(fecha));

				enlace = articulo.selectFirst("div.article-info").selectFirst("a").attr("href");
				noticia.setEnlace(enlace);

				enlaceFoto = articulo.selectFirst("div.article-photo").selectFirst("img").attr("src");
				noticia.setEnlaceFoto(enlaceFoto);

				titulo = articulo.selectFirst("div.article-info").selectFirst("div.article-title").selectFirst("span")
						.text();
				noticia.setTitulo(titulo);

				resumen = articulo.selectFirst("div.article-info").selectFirst("div.article-intro").selectFirst("p")
						.text();
				noticia.setResumen(resumen);

				noticiaList.add(noticia);
			}

			browser.close();
		} catch (Exception e) {
			throw new CustomInternalServerErrorException("");
		}
		return noticiaList.toArray(Noticia[]::new);
	}

}
