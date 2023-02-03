package com.bookit.offers;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer {

    @Id
    private String id;

    @NonNull
    private String address;

    private String description;

    @NonNull
    private String email;

    //TODO może zdjęcie?

}
