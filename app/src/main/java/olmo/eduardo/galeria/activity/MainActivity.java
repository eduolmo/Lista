package olmo.eduardo.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import olmo.eduardo.galeria.R;
import olmo.eduardo.galeria.adapter.MyAdapter;
import olmo.eduardo.galeria.model.MainActivityViewModel;
import olmo.eduardo.galeria.model.MyItem;
import olmo.eduardo.galeria.model.Util;

public class MainActivity extends AppCompatActivity {

    //variavel que vai identificar quais é a request
    static int NEW_ITEM_REQUEST = 1;
    //estabelecendo que essa classe tem um adapter
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pegando o botao de adicionar item pelo id
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //configurando o evento de click do botao de adicionar item
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //criando intent que vai navegar desta tela para a tela de "cadastro" de uma novo item
                Intent i = new Intent(MainActivity.this,NewItemActivity.class);
                //indo para a tela de "cadastrar" um item, porém esperando resposta
                startActivityForResult(i, NEW_ITEM_REQUEST);

            }
        });

        //pegando a lista de itens na tela principal pelo id
        RecyclerView rvItens = findViewById(R.id.rvItens);

        //criando um novo view model
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //pegando a lista de itens armazenados pelo view model
        List<MyItem> itens = vm.getItens();

        //criando um novo adapter
        myAdapter = new MyAdapter(this,itens);

        //estabelecendo que o novo adapter será responsável por configurar a lista de itens
        rvItens.setAdapter(myAdapter);

        //estabelecendo que não tem variação do tamanho dos itens da lista
        rvItens.setHasFixedSize(true);

        //criando um gerenciar de layout linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //estabelecendo que o layout da lista vai ser linear
        rvItens.setLayoutManager(layoutManager);

        //criando o divisor de itens
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);

        //colocando o divisor de itens na lista
        rvItens.addItemDecoration(dividerItemDecoration);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //conferindo se a resposta recebida é corresponde ao request enviado
        if(requestCode == NEW_ITEM_REQUEST){
            //conferindo se a resposta tem dados
            if(resultCode == Activity.RESULT_OK){
                //criando um novo item
                MyItem myItem = new MyItem();
                //inserindo o título no item
                myItem.title = data.getStringExtra("title");
                //inserindo a descrição no item
                myItem.description = data.getStringExtra("description");
                //pegando a URI da imagem selecionada
                Uri selectedPhotoURI = data.getData();
                try {
                    //copiando a imagem a partir da URI
                    Bitmap photo = Util.getBitmap(MainActivity.this,selectedPhotoURI,100,100);
                    //adicionando a imagem no item
                    myItem.photo = photo;
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }

                //criando um novo view model
                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

                //pegando a lista de itens armazenados pelo view model
                List<MyItem> itens = vm.getItens();

                //inserindo o novo item na lista de itens
                itens.add(myItem);
                //notificando que um novo item foi inserido na lista
                myAdapter.notifyItemInserted(itens.size()-1);

            }
        }


    }

}