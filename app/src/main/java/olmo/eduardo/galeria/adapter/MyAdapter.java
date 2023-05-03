package olmo.eduardo.galeria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import olmo.eduardo.galeria.R;
import olmo.eduardo.galeria.activity.MainActivity;
import olmo.eduardo.galeria.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter{
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //criando um inflater
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //utilizando o inflater para criar um elemento
        View v = inflater.inflate(R.layout.item_list,parent,false);
        //guardando a nova view dentro do MyViewHolder
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        //pegando um item com base na posição passada como parâmetro
        MyItem myItem = itens.get(position);
        //pegando a view que estava guardada no ViewHolder passado como parâmetro
        View v = holder.itemView;
        //pegando um campo de imagem pelo id
        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        //colocando uma imagem dentro do campo de imagem
        imvPhoto.setImageBitmap(myItem.photo);
        //pegando o campo do título pelo id
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        //colocando texto no campo do título
        tvTitle.setText(myItem.title);
        //pegando o campo da descrição pelo id
        TextView tvDesc = v.findViewById(R.id.tvDesc);
        //colocando texto no campo da descrição
        tvDesc.setText(myItem.description);

    }

    @Override
    public int getItemCount(){
        return itens.size();
    }




}
