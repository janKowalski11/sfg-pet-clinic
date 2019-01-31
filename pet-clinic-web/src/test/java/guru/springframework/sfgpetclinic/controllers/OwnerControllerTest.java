package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest
{

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    Owner owner;

    @BeforeEach
    void setUp()
    {
        Owner owner1 = new Owner();
        owner1.setId(1L);

        Owner owner2 = new Owner();
        owner2.setId(2L);

        owners = new HashSet<>();
        owners.add(owner1);
        owners.add(owner2);

        owner = new Owner();
        owner.setId(1L);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void listOwners() throws Exception
    {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/allOwners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }


    @Test
    void findOwners() throws Exception
    {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void showOwner() throws Exception
    {
        Owner owner = new Owner();
        Long ownerId = 1L;
        owner.setId(ownerId);

        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(get("/owners/" + ownerId))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1l))));
    }

    @Test
    void processFormReturnEmpty() throws Exception
    {
        List<Owner> result = new ArrayList<>();
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(result);

        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }


    @Test
    void processFindFormReturnOne() throws Exception
    {

        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner);

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(ownerList);

        mockMvc.perform(get("/owners/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindFormReturnMany() throws Exception
    {
        Owner owner1 = new Owner();
        owner1.setId(1L);

        Owner owner2 = new Owner();
        owner2.setId(2L);

        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner1);
        ownerList.add(owner2);
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(ownerList);

        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attributeExists("selections"))
                .andExpect(model().size(2));
    }

    @Test
    void processFindFormEmptyReturnMany() throws Exception
    {
        Owner owner1 = new Owner();
        owner1.setId(1L);

        Owner owner2 = new Owner();
        owner2.setId(2L);

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(
                Arrays.asList(owner1, owner2));

        mockMvc.perform(get("/owners/").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
        ;
    }


    //todo: write tests below!
    @Test
    void initCreationForm() throws Exception
    {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().size(1));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception
    {

        when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner);

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception
    {


        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception
    {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner);

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }
}