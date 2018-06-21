package spring.ticketing.services;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ticketing.model.Client;
import spring.ticketing.model.jpa.ClientJpa;
import spring.ticketing.repositories.jpa.ClientRepository;

@Service
public class ClientsSpringService implements ClientsService {

  private final ClientRepository clientRepository;

  public ClientsSpringService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Nonnull
  @Override
  public List<ClientJpa> allClients() {
    return clientRepository.findAll();
  }

  @Nonnull
  @Override
  public Optional<ClientJpa> findClientById(Integer id) {
    return clientRepository.findById(id);
  }

  @Nonnull
  @Override
  @Transactional
  public Client createClient(Client client) {
    return clientRepository.saveAndFlush(ClientJpa.from(client));
  }

  @Nonnull
  @Override
  @Transactional
  public Client updateClient(Client client) {
    return clientRepository.saveAndFlush(ClientJpa.from(client));
  }

  @Nonnull
  @Override
  @Transactional
  public Client deleteClient(Integer id) {
    ClientJpa clientJpa = clientRepository.getOne(id);
    clientRepository.deleteById(id);
    return clientJpa;
  }
}
