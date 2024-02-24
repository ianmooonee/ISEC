package pa.isec.pa.apoio_poe.model.fsm;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.states.*;

public enum State {
    FASEINICIO, FASECONFIGURACAO, FASEGESTAOALUNOS, FASEGESTAODOCENTES, FASEGESTAOPROPOSTAS, FASEOPCOESCANDIDATURAS, FASEGESTAOCANDIDATURAS,FASELISTASALUNOS, FASELISTASPROPOSTAS, FASEATRIBUICAOPROPOSTAS, FASEATRIBUICAOORIENTADORES, FASEGESTAOORIENTADORES, FASELISTASORIENTADORES,FASECONSULTA;

    public IState createState(Context context, Data data) {
        return switch (this) {
            case FASEINICIO -> new FaseInicio(context,data);
            case FASECONFIGURACAO -> new FaseConfiguracao(context, data);
            case FASEGESTAOALUNOS -> new FaseGestaoAlunos(context, data);
            case FASEGESTAODOCENTES -> new FaseGestaoDocentes(context, data);
            case FASEGESTAOPROPOSTAS -> new FaseGestaoPropostas(context, data);
            case FASEOPCOESCANDIDATURAS -> new FaseOpcoesCandidaturas(context, data);
            case FASEGESTAOCANDIDATURAS -> new FaseGestaoCandidaturas(context, data);
            case FASELISTASALUNOS -> new FaseListasAlunos(context,data);
            case FASELISTASPROPOSTAS -> new FaseListasPropostas(context,data);
            case FASEATRIBUICAOPROPOSTAS -> new FaseAtribuicaoPropostas(context, data);
            case FASEATRIBUICAOORIENTADORES -> new FaseAtribuicaoOrientadores(context, data);
            case FASEGESTAOORIENTADORES -> new FaseGestaoOrientadores(context, data);
            case FASELISTASORIENTADORES -> new FaseListasOrientadores(context, data);
            case FASECONSULTA -> new FaseConsulta(context, data);
        };
    }
}

