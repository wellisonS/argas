package vendas;

public class RepositorioVendasLista implements RepositorioVendas {
	private Venda venda;
	private RepositorioVendasLista proximo;
	
	public RepositorioVendasLista() {
		this.venda = null;
		this.proximo = null;
	}

	@Override
	public void atualizar(Venda venda) throws VendaNaoEncontradaException{
		if(this.venda == null) {
			throw new VendaNaoEncontradaException();	
		}else if(this.venda.getId().equals(venda.getId())) {
			this.venda.setCliente(venda.getCliente());
			this.venda.setFuncionario(venda.getFuncionario());
			this.venda.setProduto(venda.getProduto());
		} else {
			this.proximo.atualizar(venda);
		}
	}

	@Override
	public void remover(String id) throws VendaNaoEncontradaException {
		if(this.venda == null) {
			throw new VendaNaoEncontradaException();
		} else if(this.venda.getId().equals(id)) {
			this.venda = this.proximo.venda;
			this.proximo = this.proximo.proximo;
		} else {
			this.proximo.remover(id);
		}
	}

	@Override
	public void cadastrar(Venda venda) throws VendaJaCadastradaException {
		if(this.venda == null) {
			this.venda = venda;
			this.proximo = new RepositorioVendasLista();
		} else if(this.venda.getId().equals(venda.getId())) {
			throw new VendaJaCadastradaException();
		} else {
			this.proximo.cadastrar(venda);
		}
	}

	@Override
	public Venda procurar(String id) throws VendaNaoEncontradaException {
		if(this.venda == null) {
			throw new VendaNaoEncontradaException();	
		} else if(this.venda.getId().equals(id)) {
			return this.venda;
		} else {
			return this.proximo.procurar(id);
		}
	}
}