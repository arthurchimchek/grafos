import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class PajekFile {
	
	public static Grafo LerPajek(String nomeArquivo) throws IOException{
		Grafo grafo = new Grafo();
		
		File file = new File(nomeArquivo);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line = br.readLine();
		while(line != null) {
			if(line.contains("*Vertices")) {
				String tmp = line;
				tmp = tmp.replaceAll("\\D+","");
				int numeroVertices = Integer.parseInt(tmp);
				for (int i = 0; i < numeroVertices;i++) {
					String vertice = br.readLine();
					String partesVertice[] = vertice.split("\"");
					String nomeVertice = partesVertice[1];
					grafo.criaVertice(Integer.parseInt(partesVertice[0].trim()), nomeVertice);
				}
			}
			else if(line.contains("*Arcs")){
				grafo.setDirecionado(true);
			}
			else if(line.contains("*Edges")){
				grafo.setDirecionado(false);
			}
			else {
				String partesAresta[] = line.split(" ");
				grafo.cria_adjacencia(Integer.parseInt(partesAresta[0]), Integer.parseInt(partesAresta[1]), Integer.parseInt(partesAresta[2]));
			}
			line = br.readLine();
		}
		
		br.close();
		
		return grafo;
	}
	
	public static void EscreverPajek(Grafo g, String fileName) throws IOException{
		PrintWriter writer = new PrintWriter(new FileWriter(fileName));
		
		writer.printf("*Vertices %d\n", g.getNumeroDeVertices());
		for(int i = 0; i < g.getNumeroDeVertices(); i++){
			Vertice tmp = g.getVertices().get(i);
			writer.printf("%d \"%s\"\n", tmp.getId(), tmp.getNome());
		}
		
		if(g.isDirecionado())
			writer.printf("*Arcs\n");
		else
			writer.printf("*Edges\n");
		
		for(int i = 0; i < g.getNumeroDeVertices(); i++){
			Map<Integer, Double> tmp = g.getListaDeAdjacencias().get(i);
			for(int k : tmp.keySet()){
				writer.printf("%d %d %f\n", i, k, tmp.get(k));
			}
		}
		
		writer.close();
	}
	
}
