package br.edu.locflix.home.filmeomdb;

public record DadosListagemOmdb(

	String titulo,
	String ano,
	String imdbID,
	String poster
	) {
	public DadosListagemOmdb (FilmeOmdb filmeOmdb) {
	    this(filmeOmdb.getTitulo(),
	    		filmeOmdb.getAno(),
	    		filmeOmdb.getImdbID(),
	    		filmeOmdb.getPoster()
	    	);
	}
}
