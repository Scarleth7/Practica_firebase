package com.example.practica_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText txtid, txtnombre, txttelefono, txtcorreo;
    private Button btnbuscar, btnmodificar, btnregistrar, btneliminar;
    private ListView lvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid= (EditText) findViewById(R.id.txtId);
        txtnombre= (EditText) findViewById(R.id.txtNombre);
        txttelefono= (EditText) findViewById(R.id.txtTelefono);
        txtcorreo= (EditText) findViewById(R.id.txtCorreo);

        btnbuscar =(Button) findViewById(R.id.btnBuscar);
        btnmodificar =(Button) findViewById(R.id.btnModificar);
        btnregistrar =(Button) findViewById(R.id.btnRegistrar);
        btneliminar =(Button) findViewById(R.id.btnEliminar);
        lvDatos = (listView) findViewById(R.id.lvDatos);

        botonBuscar();
        botonRegistrar();
        botonModificar();
    }

    private void botonRegistrar(){
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtid.getText().toString().trim().isEmpty()
                        || txtnombre.getText().toString().trim().isEmpty()
                        || txttelefono.getText().toString().trim().isEmpty()
                        || txtcorreo.getText().toString().trim().isEmpty())
                {
                    ocultarTeclado();
                    Toast.makeText(MainActivity.this, "Complete los campos faltantes!!", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nombre = txtnombre.getText().toString();
                    String telefono = txttelefono.getText().toString();
                    String correo = txtcorreo.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance(); // conexion a la base de datos
                    DatabaseReference dbref = db.getReference(Agenda.class.getSimpleName()); // referencia a la base de datos agenda

                    // evento de firebase que genera la tarea de insercion
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String aux = Integer.toString(id);
                            boolean res1 = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    res1 = true;
                                    ocultarTeclado();
                                    Toast.makeText(MainActivity.this, "Error, el ID ("+aux+") ya existe!!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            boolean res2 = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("nombre").getValue().toString().equalsIgnoreCase(nombre)){
                                    res2 = true;
                                    ocultarTeclado();
                                    Toast.makeText(MainActivity.this, "Error, el nombre ("+nombre+") ya existe!!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if(res1 == false && res2 == false){
                                Agenda agenda = new Agenda(id, nombre, telefono, correo);
                                dbref.push().setValue(agenda);
                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "Contacto registrado correctamente!!", Toast.LENGTH_SHORT).show();
                                limpiar();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } // Cierra el if/else inicial.


            }
        });
    } // Cierra el método botonRegistrar.
    private void ocultarTeclado (){
        View view = this.getCurrentFocus();
        if (view !=null){
            InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowtoken(), 0);
        }
    }
    private void limpiar(){
        txtid.setText("");
        txtnombre.setText("");
        txttelefono.setText("");
        txtcorreo.setText("");

    }




































}