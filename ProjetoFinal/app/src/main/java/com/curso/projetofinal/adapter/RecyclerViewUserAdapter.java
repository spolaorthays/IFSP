package com.curso.projetofinal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.curso.projetofinal.R;
import com.curso.projetofinal.database.MyDbHandler;
import com.curso.projetofinal.model.Usuario;

import java.util.List;

public class RecyclerViewUserAdapter extends RecyclerView.Adapter<RecyclerViewUserAdapter.ViewHolder> {
    private List<Usuario> usuarioList;
    private Context context;
    private MyDbHandler dbHandler;

    public RecyclerViewUserAdapter(List<Usuario> usuarioList, MyDbHandler dbHandler, Context context) {
        this.usuarioList = usuarioList;
        this.dbHandler = dbHandler;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_user_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Usuario usuario = usuarioList.get(i);
        viewHolder.bind(usuario);
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nome, cpf, idade, telefone, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.tv_nome);
            cpf = itemView.findViewById(R.id.tv_cpf);
            idade = itemView.findViewById(R.id.tv_idade);
            telefone = itemView.findViewById(R.id.tv_telefone);
            email = itemView.findViewById(R.id.tv_email);
        }

        private void bind(Usuario usuario){
            nome.setText(usuario.getNome());
            cpf.setText(usuario.getCpf());
            idade.setText(usuario.getIdade());
            telefone.setText(usuario.getTelefone());
            email.setText(usuario.getEmail());
        }
    }
}
