package com.bookit.offers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private Environment env;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String addOffer(OfferDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        return (offerRepository.save(offer)).getId();
    }

    public OfferDTO getOffer(String id) {
        Offer offer = offerRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(offer, OfferDTO.class);
    }

    public List<OfferDTO> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class)).collect(Collectors.toList());
    }

    public List<OfferDTO> searchOffers(Date checkin, Date checkout) {
        WebClient client = WebClient.create();
        List <String> ids = client.get().uri(uriBuilder -> uriBuilder.path(env.getProperty("reservations.url.search"))
                .queryParam("checkin", dateFormat.format(checkin))
                .queryParam("checkout", dateFormat.format(checkout)).build()).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<String>>(){}).block();
        return offerRepository.findByIdIsNotIn(ids).stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class)).collect(Collectors.toList());
    }

}
