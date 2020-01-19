package br.com.stoom.backend.qualification.api.v1;

import br.com.stoom.backend.qualification.model.Address;
import br.com.stoom.backend.qualification.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping(value = "/v1/address/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Address> deleteAddress(@PathVariable(value = "id") long id){
        try {
            Optional<Address> address = addressRepository.findById(id);
            if(address.isPresent()) {
                Address deleteAddress = address.get();
                addressRepository.delete(deleteAddress);
                return new ResponseEntity<Address>(HttpStatus.OK);
            }else{
                return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/v1/address", method = RequestMethod.GET)
    public List<Address> getAll()
    {
        return addressRepository.findAll();
    }

    @RequestMapping(value = "/v1/address/{id}", method = RequestMethod.GET)
    public ResponseEntity<Address> getById(@PathVariable(value = "id") long id)
    {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent())
            return new ResponseEntity<Address>(address.get(), HttpStatus.OK);
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/v1/address/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") long id, @Valid @RequestBody Address address){
        try {
            Optional<Address> oldAddress = addressRepository.findById(id);
            if(oldAddress.isPresent()) {
                Address updateAddress = oldAddress.get();
                updateAddress.setStreetName(address.getStreetName());
                updateAddress.setCity(address.getCity());
                updateAddress.setComplement(address.getComplement());
                updateAddress.setCountry(address.getCountry());
                updateAddress.setNeighbourhood(address.getNeighbourhood());
                updateAddress.setNumber(address.getNumber());
                updateAddress.setLatitude(address.getLatitude());
                updateAddress.setLongitude(address.getLongitude());
                updateAddress.setState(address.getState());
                updateAddress.setZipcode(address.getZipcode());
                updateAddress = addressRepository.save(updateAddress);
                return new ResponseEntity<Address>(updateAddress,HttpStatus.OK);
            }else{
                return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/v1/address",method = RequestMethod.POST)
    public Address newAddress(@Valid @RequestBody Address address){
        return addressRepository.save(address);
    }

}
