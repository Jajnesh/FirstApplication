package com.example.firstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    private val userVM: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val users by userVM.users.observeAsState(emptyList())
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp), color = MaterialTheme.colorScheme.background) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2)
                ) {
                    items(users) {user ->
                        UserItem(user = user)
                    }
                }
            }
        }
    }

    @Composable
    fun UserItem(user: User) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = user.username, color = Color(128,99,250))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user.email, color = Color.Blue)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user.phone, color = Color(78,95,60))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user.name.fname, color = Color.Magenta)
            }
        }
    }
}


//Model Class
data class User(
    val email: String,
    val username: String,
    val phone: String,
    val name: Name
)

data class Name(
    @SerializedName("firstname")
    val fname: String,
    @SerializedName("lastname")
    val lname: String
)

interface  ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

object RetrofitClient {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val  apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}

class UserViewModel: ViewModel() {
    private  val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private  val repository = UserRepository(RetrofitClient.apiService)
    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.getUsers()
                _users.postValue(userList)

            } catch (e: Exception) {

            }
        }
    }
}