package br.com.cowtysys.challenge.service.file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.cowtysys.challenge.service.register.GraphRegisterService;

@Service
public class GraphFileParserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GraphFileParserService.class);
	
	@Autowired private GraphRegisterService graphRegisterService; 
	
	public void parseAndSaveData(MultipartFile file){
		LOGGER.trace("saving file data...");
		BufferedReader br = null;
		try{
			br = getBufferedReader(file);
			String line;
			while ((line = br.readLine()) != null) {
				getGraphRegisterService().registerGraph(line);
			}
		}catch(IOException e){
			LOGGER.error("Error on parser graph file.", e);
		}finally{
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOGGER.error("Error on close streaming buffer.", e);
				}
			}
		}
	}

	public BufferedReader getBufferedReader(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		InputStream is = new ByteArrayInputStream(bytes);
		
		return new BufferedReader(new InputStreamReader(is));
	}
	
	public GraphRegisterService getGraphRegisterService() {
		return graphRegisterService;
	}

}
