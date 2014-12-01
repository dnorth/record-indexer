package server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.database.DatabaseException;
import server.facade.ServerFacade;
import shared.communication.GetProjects_Result;
import shared.communication.GetProjects_Result.ProjectInfo;
import shared.model.Project;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GetProjectsHandler implements HttpHandler{
	Logger logger; 
	private XStream xmlStream = new XStream(new DomDriver());
	
	@Override
	public void handle(HttpExchange exchange) throws IOException{
		logger = Logger.getLogger("record-indexer");
		List<Project> p = null;
		
		try {
			p = ServerFacade.getProjects();
		}
		catch (ServerException | DatabaseException e)
		{
			//Server failure
			logger.log(Level.SEVERE, e.getMessage(), e);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
			return;
		}
		if(p != null)
		{
			List<ProjectInfo> infoList = new ArrayList<ProjectInfo>();
			GetProjects_Result result = new GetProjects_Result(infoList);
			for(Project pro : p)
			{
				infoList.add(result.new ProjectInfo(pro.getId(), pro.getTitle()));
			}
			result.setInfo(infoList);
		
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			xmlStream.toXML(result, exchange.getResponseBody());
			exchange.getResponseBody().close();
		}
		else
		{
			logger.log(Level.WARNING, "Could not get Projects");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, -1);
		}
	}
}

