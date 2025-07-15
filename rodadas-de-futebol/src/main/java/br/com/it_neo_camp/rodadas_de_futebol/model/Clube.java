    package br.com.it_neo_camp.rodadas_de_futebol.model;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;

    import java.time.LocalDate;


    @Entity
    @Table(name = "clubes")
    public class Clube {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @NotNull
        @Size(min = 2)
        private String nome;
        private Integer totalPontos;
        private Integer totalGols;
        private Integer totalVitorias;
        private Integer totalJogos;
        @NotBlank
        private String siglaEstado;
        private String estadoClube;
        private LocalDate dataCriacao;
        @NotNull
        private boolean statusClube;
        @Column(nullable = false)
        private boolean ativo = true;

        public boolean isAtivo() {
            return ativo;
        }

        public void setAtivo(boolean ativo) {
            this.ativo = ativo;
        }

        public long getId() {
            return id;
        }

        public void setId(long clubeId) {
            this.id = clubeId;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nomeClube) {
            this.nome = nomeClube;
        }

        public Integer getTotalPontos() {
            return totalPontos;
        }

        public void setTotalPontos(Integer totalPontos) {
            this.totalPontos = totalPontos;
        }

        public Integer getTotalGols() {
            return totalGols;
        }

        public void setTotalGols(Integer totalGols) {
            this.totalGols = totalGols;
        }

        public Integer getTotalVitorias() {
            return totalVitorias;
        }

        public void setTotalVitorias(Integer totalVitorias) {
            this.totalVitorias = totalVitorias;
        }

        public Integer getTotalJogos() {
            return totalJogos;
        }

        public void setTotalJogos(Integer totalJogos) {
            this.totalJogos = totalJogos;
        }

        public String getSiglaEstado() {
            return siglaEstado;
        }

        public void setSiglaEstado(String siglaEstado) {
            this.siglaEstado = siglaEstado;
        }

        public String getEstadoClube() {
            return estadoClube;
        }

        public void setEstadoClube(String estadoClube) {
            this.estadoClube = estadoClube;
        }

        public LocalDate getDataCriacao() {
            return dataCriacao;
        }

        public void setDataCriacao(LocalDate dataCriacao) {
            this.dataCriacao = dataCriacao;
        }

        public boolean isStatusClube() {
            return statusClube;
        }

        public void setStatusClube(boolean statusClube) {
            this.statusClube = statusClube;
        }
    }
