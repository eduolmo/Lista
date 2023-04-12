package olmo.eduardo.galeria.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import olmo.eduardo.galeria.R;
import olmo.eduardo.galeria.model.MyItem;

public class NewItemActivity extends AppCompatActivity {

    //variável que identifica os requests
    static int PHOTO_PICKER_REQUEST = 1;
    //estabelecendo que a URI da foto selecionada é inicialmente null
    Uri photoSelected = null;

    @Override
    protected void onActivityResult(int resquestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(resquestCode,resultCode,data);
        //conferindo se a resposta recebida corresponde ao request
        if(resquestCode == PHOTO_PICKER_REQUEST){
            //conferindo se a resposta tem dados
            if(resultCode == Activity.RESULT_OK){
                //pegando os dados da resposta e colocando na variavel
                photoSelected = data.getData();
                //pegando o campo de imagem pelo id
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                //colocando a imagem selecionada dentro do campo de imagem
                imvfotoPreview.setImageURI(photoSelected);

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        //pegando o botao da galeria pelo id
        ImageButton imgCI = findViewById(R.id.imbCI);
        //estabelendo o evento de click no botao
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //criando uma intenção implícita, onde um documento será aberto
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //estabelecendo que esse documento deve ser uma imagem
                photoPickerIntent.setType("image/*");
                //executando a intenção de pegar uma imagem, porém esperando uma respsota
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST);

            }
        });
        //pegando o botão de adicionar item pelo id
        Button btnAddItem = findViewById(R.id.btnAddItem);
        //estabelecendo o evento de click do botão
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //conferindo se o usuario pegou foto
                if(photoSelected == null){
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                //pegando o campo do título pelo id
                EditText etTitle = findViewById(R.id.etTitle);
                //pegamdo o texto do título
                String title = etTitle.getText().toString();
                //conferindo se o título está vazio
                if(title.isEmpty()){
                    //disparando um aviso caso o título esteja vazio
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                //pegando o campo da descrição pelo id
                EditText etDesc = findViewById(R.id.etDesc);
                //pegando o texto da descrição
                String description = etDesc.getText().toString();
                //conferindo se a descrição está vazia
                if(description.isEmpty()){
                    //disparando um aviso caso o descrição esteja vazio
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }
                //criando uma nova intenção
                Intent i = new Intent();
                //colocando a URI da imagem na intent
                i.setData(photoSelected);
                //colocando o título na intent
                i.putExtra("title",title);
                //colocando a descrição na intent
                i.putExtra("description",description);
                //informando que tem dados dentro da intent
                setResult(Activity.RESULT_OK, i);
                //devolvendo a resposta para a MainActivity
                finish();

            }
        });


    }
}