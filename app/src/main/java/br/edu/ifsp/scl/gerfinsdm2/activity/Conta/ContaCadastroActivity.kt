package br.edu.ifsp.scl.gerfinsdm2.activity.Conta

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.gerfinsdm2.R
import br.edu.ifsp.scl.gerfinsdm2.data.ContaSQLite
import br.edu.ifsp.scl.gerfinsdm2.model.Conta
import kotlinx.android.synthetic.main.activity_cadconta.*

class ContaCadastroActivity: AppCompatActivity() {

    private lateinit var dao: ContaSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadconta)

        dao = ContaSQLite(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cadastro, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Tratamento do evento de clique nos ícones da barra de menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Clique no ícone para salvar os dados
            R.id.action_salvar -> {
                val nome = etNomeConta.text.toString()
                val saldoinicial = etSaldoConta.text.toString()

                //Verificando se o usuário preencheu todos os EditText
                if (etNomeConta.text.isNotEmpty() && etSaldoConta.text.toString().isNotEmpty()) {
                    // Capturando os dados dos EditText e inserindo no banco de dados
                    var c = Conta()
                    c.nome = nome
                    c.saldoinicial = saldoinicial
                    dao.criaConta(c)

                    Toast.makeText(this, "Conta salva com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }else {
                    //Caso usuário não tenha preenchido todos os campos é apresentado consulta_transacao
                    etNomeConta.error = if (etNomeConta.text.isEmpty())
                        "Digite o nome da conta" else null
                    etSaldoConta.error = if (etSaldoConta.text.isEmpty())
                        "Digite o saldo inicial da conta" else null
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


