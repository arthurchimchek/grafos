import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class LeitorArquivos {
	private final String DIRETORIO = "C:\\www\\grafos\\base_eron\\amostra";
	private final String SENT_ITEMS = "sent_items";
	private final String SENT_EMAIL = "_sent_mail";
	private File root;
	
	public LeitorArquivos()
	{
		this.root = new File(DIRETORIO);
	}
	
	public Grafo readDirectory() throws IOException{
		Grafo grafo;
		String[] pessoas = root.list(new FilenameFilter()
		{
			public boolean accept(File current,String name)
			{
				return new File(current, name).isDirectory();
			}
		});
		
		grafo = new Grafo(pessoas.length);			
		
		for(int i = 0; i < pessoas.length; i++)
		{
			String pessoa = pessoas[i];
			String diretorioPrincipal = DIRETORIO + "\\" + pessoa + "\\" + SENT_ITEMS;
			String diretorioSecundario = DIRETORIO + "\\" + pessoa + "\\" + SENT_EMAIL;
			
			File diretorioPessoa = new File(diretorioPrincipal);
			if(!diretorioPessoa.exists())
				diretorioPessoa = new File(diretorioSecundario);
			
			File[] emails = diretorioPessoa.listFiles();
			FileReader reader;
			BufferedReader br;
			String linha;
			
			for(int j = 0; j < emails.length; j++)
			{
				File email = emails[i];
				reader = new FileReader(email);
				br = new BufferedReader(reader);
				linha = br.readLine();
				String from = "", to = "";
				
				while(linha != null)
				{
					if(linha.startsWith("From:"))
						from = linha;
					else if(linha.startsWith("To:"))
						to = linha;
					
					if(!from.equals("") && !to.equals(""))
						break;
					
					linha = br.readLine();
				}
				
				from = from.split(":")[1].trim();
				to = to.split(":")[1].trim();
				
				int idFrom = grafo.procura_vertice(from);
				int idTo = grafo.procura_vertice(to);
				
				if(idFrom == -1)
					grafo.seta_informacao(grafo.get_size(), from);
				if(idTo == -1)
					grafo.seta_informacao(grafo.get_size(), to);
				
				
			}
			
		}
		
		System.out.println(Arrays.toString(pessoas));
		
		return null;
	}
}
