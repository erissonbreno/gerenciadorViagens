package com.montanha.isolada.utils;

import com.montanha.isolada.dtos.ViagemDtoResponse;
import com.montanha.isolada.entities.Viagem;

public abstract class Conversor {

    public static ViagemDtoResponse converterViagemToViagemDtoResponse(Viagem viagem) {

        ViagemDtoResponse viagemDtoResponse = new ViagemDtoResponse();
        viagemDtoResponse.setId(viagem.getId());
        viagemDtoResponse.setAcompanhante(viagem.getAcompanhante());
        viagemDtoResponse.setDataPartida(viagem.getDataPartida());
        if (viagem.getDataRetorno() != null) {
            viagemDtoResponse.setDataRetorno(viagem.getDataRetorno());
        }
        if (viagem.getLocalDeDestino() != null) {
            viagemDtoResponse.setLocalDeDestino(viagem.getLocalDeDestino());
        }

        if (viagem.getRegiao() != null) {
            viagemDtoResponse.setRegiao(viagem.getRegiao());
        }


        return viagemDtoResponse;
    }
}
