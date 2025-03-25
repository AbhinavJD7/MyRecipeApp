package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    // Now we will create a data class that will take care of the state of the
    // recipe which will take care that we have the recipe or not
    private val _categoriesState = mutableStateOf(RecipeState()) // mutableStateOf activates changes in the user interface
    val categoriesState: State<RecipeState> = _categoriesState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {// this coroutine helps us to call the suspend functions ..because in order to get soemthing from the internet we need to put it into suspend function
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error Fetching Categories ${e.message}"

                )
            }
        }
    }

   data class RecipeState(
       val loading : Boolean = true,
       val list: List<Category> = emptyList(),
       val error: String? = null
   )

}