package com.evrencoskun.tableviewsample2.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.evrencoskun.tableviewsample2.data.UserRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link com.evrencoskun.tableviewsample2.data.UserRepository}
 */
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final UserRepository userRepository;

    public MainViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(userRepository);
    }
}
