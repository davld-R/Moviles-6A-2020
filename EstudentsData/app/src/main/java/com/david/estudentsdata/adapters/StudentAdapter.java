package com.david.estudentsdata.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.david.estudentsdata.MainActivity;
import com.david.estudentsdata.R;
import com.david.estudentsdata.models.Student;
import com.david.estudentsdata.ui.gallery.GalleryFragment;
import com.david.estudentsdata.utili.DBM;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentsViewHolder> {

    Context context;
    List<Student> list;
    DBM dbm;
    //Button btn_del;
    //Button btn_mod;
    View view;
    GalleryFragment gf;

    public StudentAdapter(Context context, List<Student> list, GalleryFragment org) {
        this.context = context;
        this.list = list;
        dbm = new DBM(context);
        gf = org;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student, parent, false);
        StudentsViewHolder svh = new StudentsViewHolder(view);

        context = view.getContext();

        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentsViewHolder holder, int position) {

        final Student s = list.get(position);

        holder.idTv.setText(s.getId() + "");
        holder.nameTv.setText(s.getName());
        holder.genderTv.setText(s.isGender() ? "Masculino" : "Femenino");
        holder.ageTv.setText(s.getAge() + "");
        holder.codeTv.setText(s.getCode());
        holder.semesterTv.setText(s.getSemester() + "");

        //Eventos a los botones
        //Delete: debe abrir un dialogo de confirmación

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("¿Esta seguro que desea borrar este registro?")
                        .setTitle("Borrar registro")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                boolean ret = dbm.deleteStudentById(s);
                                if (ret) {
                                    gf.list = dbm.listStudents();
                                    gf.adapter = new StudentAdapter(gf.getContext(), gf.list, gf);
                                    gf.rv.setAdapter(gf.adapter);
                                    Toast.makeText(context, "Se ha borrado el registro", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();
            }
        });

      /*  btn_del = view.findViewById(R.id.btn_del);

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Desea Eliminar este registro")
                        .setTitle("Eliminar")
                        .setCancelable(false)
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Registro Eliminado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                builder.show();

            }
        });*/

        //Update: Debe abrir un dialogo con el formulario para llenar los datos

        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = gf.getLayoutInflater();

                View v = inflater.inflate(R.layout.dialog_mod, null);

                final EditText idEt = v.findViewById(R.id.id_et);
                final EditText nameEt = v.findViewById(R.id.name_et);
                RadioGroup genderRg = v.findViewById(R.id.gender_et);
                final RadioButton rbMan = v.findViewById(R.id.man_rb);
                final RadioButton rbWomen = v.findViewById(R.id.women_rb);
                final EditText ageEt = v.findViewById(R.id.age_et);
                final EditText codeEt = v.findViewById(R.id.code_et);
                final EditText semesterEt = v.findViewById(R.id.semester_et);
                final Button btnSave = v.findViewById(R.id.save_btn);

                idEt.setText(s.getId()+"");
                idEt.setEnabled(false);
                nameEt.setText(s.getName());
                rbMan.setChecked(s.isGender());
                rbWomen.setChecked(s.isGender());
                ageEt.setText(s.getAge()+"");
                codeEt.setText(s.getCode());
                semesterEt.setText(s.getSemester()+"");

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        s.setName(nameEt.getText().toString());
                        s.setGender(rbMan.isChecked() == true ? true : false);
                        s.setAge(Short.parseShort(ageEt.getText().toString()));
                        s.setCode(codeEt.getText().toString());
                        s.setSemester(Integer.parseInt(semesterEt.getText().toString()));


                        boolean ret = dbm.updateStudent(s);

                        if (ret == true) {
                            Toast.makeText(context, "Registro Actualizado", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Error al guardar el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setView(v)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {



                            }
                        });
                builder.setCancelable(true);
                builder.show();
            }
        });


        /*btn_mod = view.findViewById(R.id.btn_upd);

        btn_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);

                View vw = inflater.inflate(R.layout.dialog_mod, null);

                builder.setView(vw)
                        .setTitle("Cambiar Datos")
                        .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Datos Actualizados", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /*
            Clase anidada para controlar los elementos de la interfaz
        */
    public static class StudentsViewHolder extends RecyclerView.ViewHolder {

        TextView idTv;
        TextView nameTv;
        TextView genderTv;
        TextView ageTv;
        TextView codeTv;
        TextView semesterTv;
        Button btn_delete;
        Button btn_update;


        public StudentsViewHolder(View itemView) {
            super(itemView);

            idTv = itemView.findViewById(R.id.id_tv);
            nameTv = itemView.findViewById(R.id.name_tv);
            genderTv = itemView.findViewById(R.id.gender_tv);
            ageTv = itemView.findViewById(R.id.age_tv);
            codeTv = itemView.findViewById(R.id.code_tv);
            semesterTv = itemView.findViewById(R.id.semester_tv);
            btn_delete = itemView.findViewById(R.id.btn_del);
            btn_update = itemView.findViewById(R.id.btn_upd);


        }
    }


}

