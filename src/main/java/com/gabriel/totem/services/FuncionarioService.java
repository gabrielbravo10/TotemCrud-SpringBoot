package com.gabriel.totem.services;

import com.gabriel.totem.models.Funcionario;
import com.gabriel.totem.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Page<Funcionario> listAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public Funcionario listOne(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void delete(Long id) {
        Funcionario funcionario = listOne(id);
        funcionarioRepository.delete(funcionario);
    }
}
