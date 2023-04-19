package olmo.eduardo.galeria.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    //lista que armazena os itens da activity
    List<MyItem> itens = new ArrayList<>();
    //método que retorna a lista de itens da activity
    public List<MyItem> getItens() {
        return itens;
    }
}
