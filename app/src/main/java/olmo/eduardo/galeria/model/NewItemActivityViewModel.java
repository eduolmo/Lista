package olmo.eduardo.galeria.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    //variável que armazena a URI da foto selecionada
    Uri selectedPhotoLocation = null;
    //método que retorna a URI da foto selecionada
    public Uri getSelectedPhotoLocation() {
        return selectedPhotoLocation;
    }
    //método que muda a URI da variável "selectedPhotoLocation"
    public void setSelectedPhotoLocation(Uri selectedPhotoLocation) {
        this.selectedPhotoLocation = selectedPhotoLocation;
    }
}
