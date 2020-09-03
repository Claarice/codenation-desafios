package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Comparator;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<Time> listaDeTimes = new ArrayList<Time>(); 
	List<Jogador> listaDeJogadores = new ArrayList<Jogador>();
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (buscarTime(id) != null) 
			throw new IdentificadorUtilizadoException();
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		listaDeTimes.add(time);
	}
	
	private Time buscarTime(Long idTime) {
		return Optional.of(listaDeTimes.stream().filter(time -> time.getId().equals(idTime)).findFirst()).get().orElse(null);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (buscarJogador(id) != null)
			throw new IdentificadorUtilizadoException();
		if (buscarTime(idTime) == null) 
			throw new TimeNaoEncontradoException();
		Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		listaDeJogadores.add(jogador);
	}
		
	private Jogador buscarJogador(Long idJogador) {
		return Optional.of(listaDeJogadores.stream().filter(jogador -> jogador.getId().equals(idJogador)).findFirst()).get().orElse(null);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);
		if (buscarJogador(idJogador) == null)
			throw new JogadorNaoEncontradoException();
		Long idTime = jogador.getIdTime();
		Time time = buscarTime(idTime);
		time.setIdCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = buscarTime(idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();
		if (time.getIdCapitao() == null)
			throw new CapitaoNaoInformadoException();
		return time.getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);
		if (jogador == null)
			throw new JogadorNaoEncontradoException();
		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = buscarTime(idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();
		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = buscarTime(idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();
		return Optional.of(listaDeJogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.sorted(Comparator.comparing(Jogador::getId))
				.map(jogador -> jogador.getId()).collect(Collectors.toList())).orElse(null);
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = buscarTime(idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();
		return listaDeJogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed())
				.map(jogador -> jogador.getId()).findAny().get();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = buscarTime(idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();
		return listaDeJogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.sorted((e1, e2) -> e1.getDataNascimento().compareTo(e2.getDataNascimento()))
				.map(jogador -> jogador.getId()).findFirst().get();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return Optional.of(listaDeTimes.stream()
				.sorted(Comparator.comparingLong(Time::getId))
				.map(time -> time.getId())
				.collect(Collectors.toList())).orElse(null);
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = buscarTime(idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();
		return listaDeJogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.sorted((e1, e2) -> e2.getSalario().compareTo(e1.getSalario()))
				.map(jogador -> jogador.getId()).findFirst().get();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);
		if (jogador == null)
			throw new JogadorNaoEncontradoException();
		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return Optional.of(listaDeJogadores.stream()
				.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
				.map(jogador -> jogador.getId())
				.limit(top).collect(Collectors.toList())).orElse(null);
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = buscarTime(timeDaCasa);
		Time timeFora = buscarTime(timeDeFora);
		if (timeCasa == null)
			throw new TimeNaoEncontradoException();
		if (timeFora == null)
			throw new TimeNaoEncontradoException();
		
		return (timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())) ? timeFora.getCorUniformeSecundario() : timeFora.getCorUniformePrincipal();
	}

}
