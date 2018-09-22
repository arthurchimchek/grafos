import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorArquivos {
	private final String DIRETORIO = "C:\\www\\grafos\\base_eron\\amostra";
//	private final String DIRETORIO = "D:\\Dev\\amostra-enron";
	private final String SENT_ITEMS = "sent_items";
	private final String SENT_EMAIL = "_sent_mail";
	private File root;

	public LeitorArquivos()
	{
		this.root = new File(DIRETORIO);
	}

	public Grafo readDirectory() throws IOException {
		Grafo grafo;
		String[] pessoas = root.list(new FilenameFilter()
		{
			public boolean accept(File current,String name)
			{
				return new File(current, name).isDirectory();
			}
		});

		grafo = new Grafo();

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
				File email = emails[j];
				reader = new FileReader(email);
				br = new BufferedReader(reader);
				linha = br.readLine();
				String remetente = "", destinatario = "";
				int idRemetente, idDestinatario;
				
				while(linha != null)
				{
					if(linha.startsWith("From:")) {
						remetente = linha;
						destinatario = br.readLine();
						break;
					}
					linha = br.readLine();
				}
				
				if(!destinatario.startsWith("To:")) {
					continue;
				}

				remetente = remetente.split(":")[1].trim();

				idRemetente = grafo.procuraVertice(remetente);

				if(idRemetente == -1) {
					grafo.criaVertice(remetente);
					idRemetente = grafo.getNumeroDeVertices() - 1;
				}

				List<String> destinatarios = new ArrayList<>();
				try {
					destinatario = destinatario.split(":")[1].trim();
					for(String d : destinatario.split(",")) {
						d = d.trim();
						destinatarios.add(d);
					}

					if(destinatario.endsWith(",")) {
						destinatario = br.readLine().trim();
						destinatario = destinatario.split(":")[1].trim();
						for(String d : destinatario.split(",")) {
							d = d.trim();
							destinatarios.add(d);
						}
					}
				} catch(Exception e) {
					continue;
				}

				for(String d : destinatarios) {
					idDestinatario = grafo.procuraVertice(d);
					if(idDestinatario == -1) {
						grafo.criaVertice(d);
						idDestinatario = grafo.getNumeroDeVertices() - 1;
					}

					grafo.criaAdjacencia(idRemetente, idDestinatario);
				}
			}

		}

		System.out.println(Arrays.toString(pessoas));

		return grafo;
	}
}
