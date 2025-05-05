package Unisecure.model;

import java.time.LocalDateTime;

public class Emergencia {
    private int id;
    private String localidade;
    private String tiposEmergencia;
    private LocalDateTime dataHora;

    public Emergencia() {}

    public Emergencia(String localidade, String tiposEmergencia) {
        this.localidade = localidade;
        this.tiposEmergencia = tiposEmergencia;
        this.dataHora = LocalDateTime.now();
    }

    public Emergencia(int id, String localidade, String tiposEmergencia, LocalDateTime dataHora) {
        this.id = id;
        this.localidade = localidade;
        this.tiposEmergencia = tiposEmergencia;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public String getLocalidade() { 
        return localidade; 
    }
    public void setLocalidade(String localidade) { 
        this.localidade = localidade; 
    }

    public String getTiposEmergencia() { 
        return tiposEmergencia; 
    }
    public void setTiposEmergencia(String tiposEmergencia) { 
        this.tiposEmergencia = tiposEmergencia; 
    }

    public LocalDateTime getDataHora() { 
        return dataHora; 
    }
    public void setDataHora(LocalDateTime dataHora) { 
        this.dataHora = dataHora; 
    }

    @Override
    public String toString() {
        return "Emergencia [id=" + id + ", localidade=" + localidade + ", tipos=" + tiposEmergencia + ", data=" + dataHora + "]";
    }
}
