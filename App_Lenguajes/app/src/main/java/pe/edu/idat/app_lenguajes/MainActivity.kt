package pe.edu.idat.app_lenguajes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import pe.edu.idat.app_lenguajes.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurando los onClickListeners para los botones
        binding.btnguadar.setOnClickListener(this)
        binding.btnmostrar.setOnClickListener(this)
        binding.btnactualizar.setOnClickListener(this)
        binding.btneliminar.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnguadar -> guardarLenguaje()
            R.id.btnmostrar -> mostrarLenguajes()
            R.id.btnactualizar -> actualizarLenguaje()
            R.id.btneliminar -> eliminarLenguaje()
        }
    }

    private fun guardarLenguaje() {
        val url = "http://192.168.1.171:4000/api/languages/"
        val jsonObject = JSONObject().apply {
            put("name", binding.txtlenguaje.text.toString())
            put("programmers", binding.txtprogramador.text.toString())
        }
        val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                Toast.makeText(this, "Lenguaje guardado exitosamente", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }

    private fun mostrarLenguajes() {
        val url = "http://192.168.1.171:4000/api/languages/"
        val request = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                for (i in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(i)
                    val id = jsonObject.getInt("id")
                    val name = jsonObject.getString("name")
                    val programmers = jsonObject.getString("programmers")
                    // Hacer lo que necesites con los datos
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }

    private fun actualizarLenguaje() {
        val id = binding.txtid.text.toString()
        val url = "http://192.168.1.171:4000/api/languages/$id"
        val jsonObject = JSONObject().apply {
            put("name", binding.txtlenguaje.text.toString())
            put("programmers", binding.txtprogramador.text.toString())
        }
        val request = JsonObjectRequest(Request.Method.PUT, url, jsonObject,
            Response.Listener { response ->
                Toast.makeText(this, "Lenguaje actualizado exitosamente", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }

    private fun eliminarLenguaje() {
        val id = binding.txtid.text.toString()
        val url = "http://192.168.1.171:4000/api/languages/$id"
        val request = JsonObjectRequest(Request.Method.DELETE, url, null,
            Response.Listener { response ->
                Toast.makeText(this, "Lenguaje eliminado exitosamente", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }
}
