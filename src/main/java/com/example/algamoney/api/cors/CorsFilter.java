package com.example.algamoney.api.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.algamoney.api.config.property.AlgamoneyApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;// TODO Configuração para diferentes ambientes

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// setando Headers do filtro com CORS
		resp.setHeader("Access-control-Allow-Origin", algamoneyApiProperty.getOriginPermitida());
		resp.setHeader("Access-control-Allow-Credentials", "true");

		if ("OPTIONS".equals(req.getMethod()) && algamoneyApiProperty.getOriginPermitida().equals(req.getHeader("Origin"))) {
			resp.setHeader("Access-control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
			resp.setHeader("Access-control-Allow-Headers", "Authorization, Content-Type, Accept");
			resp.setHeader("Access-control-Max-Age", "3600");

			resp.setStatus(HttpServletResponse.SC_OK);

		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
