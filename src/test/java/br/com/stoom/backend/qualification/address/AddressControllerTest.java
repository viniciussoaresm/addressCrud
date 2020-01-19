package br.com.stoom.backend.qualification.address;

import br.com.stoom.backend.qualification.api.v1.AddressController;
import br.com.stoom.backend.qualification.pojo.model.Address;
import br.com.stoom.backend.qualification.repository.AddressRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AddressController.class)
public class AddressControllerTest {

    private Long addressTestId = null;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private AddressRepository addressRepository;

    @Autowired
    private AddressController addressController;

    private Address getMockAddress() {
        Address address = new Address();
        address.setStreetName("Rua 1");
        address.setCity("Campinas");
        address.setComplement("");
        address.setCountry("Brasil");
        address.setNeighbourhood("Botafogo");
        address.setZipcode(13064000);
        address.setState("São Paulo");

        return address;
    }


    @Test
    public void createAddress(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Address address = getMockAddress();
        Address newAddress = addressController.newAddress(address);
        addressTestId = newAddress.getId();

        assertThat(newAddress != null);
    }

    @Test
    public void testFindById()
    {
        // given
        Address addressMock = getMockAddress();

        if (addressTestId != null) {
            ResponseEntity<Address> responseEntity = addressController.getById(addressTestId);
            Address address = responseEntity.getBody();
            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
            assertThat(address.getStreetName())
                    .isEqualTo(addressMock.getStreetName());
        }
    }

    @Test
    public void updateById() {
        // given
        Address addressMock = getMockAddress();
        addressMock.setStreetName("UpdateName");

        if (addressTestId != null) {
            ResponseEntity<Address> responseEntity = addressController.updateAddress(addressTestId, addressMock);
            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

            ResponseEntity<Address> responseEntity2 = addressController.getById(addressTestId);
            Address address = responseEntity2.getBody();
            assertThat(responseEntity2.getStatusCodeValue()).isEqualTo(200);
            assertThat(address.getStreetName()).isEqualTo(addressMock.getStreetName());
        }
    }

    @Test
    public void deleteAddress(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        if (addressTestId != null) {
            ResponseEntity<Address> addressResponseEntity = addressController.deleteAddress(addressTestId);
            assertThat(addressResponseEntity.getStatusCodeValue()).isEqualTo(200);
            assertThat(addressResponseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
        }
    }

}
