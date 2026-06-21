package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.application.model.CreateRouteRequest;
import com.marslps.AISafeFMS.application.model.UpdateRouteRequest;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.entities.RouteHistory;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.model.vo.RouteID;
import com.marslps.AISafeFMS.repository.AirportRepository;
import com.marslps.AISafeFMS.repository.RouteHistoryRepository;
import com.marslps.AISafeFMS.repository.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private RouteHistoryRepository routeHistoryRepository;

    @Mock
    private AirportRepository airportRepository;

    private RouteServiceImpl routeService;

    @BeforeEach
    void setUp() {
        routeService = new RouteServiceImpl(
                routeRepository,
                routeHistoryRepository,
                airportRepository
        );
    }

    private Airport airport(String iata, AirportStatus status) {
        Airport airport = mock(Airport.class);

        lenient().when(airport.obtainIata()).thenReturn(new LocationIdentifier(iata));
        lenient().when(airport.obtainStatus()).thenReturn(status);

        return airport;
    }

    private Route route() {
        return new Route(
                new RouteID("TSA-TSB"),
                airport("TSA", AirportStatus.OPERATIONAL),
                airport("TSB", AirportStatus.OPERATIONAL),
                1.0,
                280.0,
                500.0,
                100,
                true
        );
    }

    private CreateRouteRequest createRouteRequest() {
        return new CreateRouteRequest(
                "TSA",
                "TSB",
                1.0,
                280.0,
                500.0,
                100
        );
    }

    private UpdateRouteRequest updateRouteRequest() {
        return new UpdateRouteRequest(
                1.2,
                280.0,
                550.0,
                120
        );
    }

    @Test
    void createRouteSuccessfully() {
        Airport departure = airport("TSA", AirportStatus.OPERATIONAL);
        Airport destination = airport("TSB", AirportStatus.OPERATIONAL);

        when(airportRepository.findByIata(any(LocationIdentifier.class)))
                .thenReturn(Optional.of(departure))
                .thenReturn(Optional.of(destination));

        when(routeRepository.existsByRouteId(new RouteID("TSA-TSB"))).thenReturn(false);
        when(routeRepository.save(any(Route.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Route result = routeService.createRoute(createRouteRequest());

        assertNotNull(result);
        assertEquals(new RouteID("TSA-TSB"), result.obtainRouteID());
        assertTrue(result.isActive());

        verify(routeRepository).save(any(Route.class));
        verify(routeHistoryRepository).save(any(RouteHistory.class));
    }

    @Test
    void createRouteSavesHistoryWithCreatedDescription() {
        Airport departure = airport("TSA", AirportStatus.OPERATIONAL);
        Airport destination = airport("TSB", AirportStatus.OPERATIONAL);

        when(airportRepository.findByIata(any(LocationIdentifier.class)))
                .thenReturn(Optional.of(departure))
                .thenReturn(Optional.of(destination));

        when(routeRepository.existsByRouteId(new RouteID("TSA-TSB"))).thenReturn(false);
        when(routeRepository.save(any(Route.class))).thenAnswer(invocation -> invocation.getArgument(0));

        routeService.createRoute(createRouteRequest());

        ArgumentCaptor<RouteHistory> captor = ArgumentCaptor.forClass(RouteHistory.class);
        verify(routeHistoryRepository).save(captor.capture());

        assertEquals("Route created.", captor.getValue().getDescription());
    }

    @Test
    void createRouteFailsWhenDepartureAirportDoesNotExist() {
        when(airportRepository.findByIata(any(LocationIdentifier.class)))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.createRoute(createRouteRequest())
        );

        assertEquals("Departure airport not found.", exception.getMessage());

        verify(routeRepository, never()).save(any(Route.class));
        verify(routeHistoryRepository, never()).save(any(RouteHistory.class));
    }

    @Test
    void createRouteFailsWhenDestinationAirportDoesNotExist() {
        Airport departure = airport("TSA", AirportStatus.OPERATIONAL);

        when(airportRepository.findByIata(any(LocationIdentifier.class)))
                .thenReturn(Optional.of(departure))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.createRoute(createRouteRequest())
        );

        assertEquals("Destination airport not found.", exception.getMessage());

        verify(routeRepository, never()).save(any(Route.class));
        verify(routeHistoryRepository, never()).save(any(RouteHistory.class));
    }

    @Test
    void createRouteFailsWhenAirportIsNotOperational() {
        Airport departure = airport("TSA", AirportStatus.OPERATIONAL);
        Airport destination = airport("TSB", AirportStatus.CLOSED);

        when(airportRepository.findByIata(any(LocationIdentifier.class)))
                .thenReturn(Optional.of(departure))
                .thenReturn(Optional.of(destination));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.createRoute(createRouteRequest())
        );

        assertEquals("Both airports must be operational.", exception.getMessage());

        verify(routeRepository, never()).save(any(Route.class));
        verify(routeHistoryRepository, never()).save(any(RouteHistory.class));
    }

    @Test
    void createRouteFailsWhenRouteAlreadyExists() {
        Airport departure = airport("TSA", AirportStatus.OPERATIONAL);
        Airport destination = airport("TSB", AirportStatus.OPERATIONAL);

        when(airportRepository.findByIata(any(LocationIdentifier.class)))
                .thenReturn(Optional.of(departure))
                .thenReturn(Optional.of(destination));

        when(routeRepository.existsByRouteId(new RouteID("TSA-TSB"))).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.createRoute(createRouteRequest())
        );

        assertEquals("Route already exists.", exception.getMessage());

        verify(routeRepository, never()).save(any(Route.class));
        verify(routeHistoryRepository, never()).save(any(RouteHistory.class));
    }

    @Test
    void getRouteHistorySuccessfully() {
        Route route = route();
        RouteID routeId = new RouteID("TSA-TSB");

        RouteHistory history1 = new RouteHistory(route, "Route created.");
        RouteHistory history2 = new RouteHistory(route, "Route updated.");

        when(routeRepository.existsByRouteId(routeId)).thenReturn(true);
        when(routeHistoryRepository.findByRouteId(routeId)).thenReturn(List.of(history1, history2));

        List<RouteHistory> result = routeService.getRouteHistory("TSA-TSB");

        assertEquals(2, result.size());
        assertEquals("Route created.", result.get(0).getDescription());
        assertEquals("Route updated.", result.get(1).getDescription());
    }

    @Test
    void getRouteHistoryFailsWhenRouteDoesNotExist() {
        RouteID routeId = new RouteID("TSA-TSB");

        when(routeRepository.existsByRouteId(routeId)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.getRouteHistory("TSA-TSB")
        );

        assertEquals("Route not found.", exception.getMessage());

        verify(routeHistoryRepository, never()).findByRouteId(any(RouteID.class));
    }

    @Test
    void updateRouteSuccessfully() {
        Route route = route();
        RouteID routeId = new RouteID("TSA-TSB");

        when(routeRepository.findByRouteId(routeId)).thenReturn(Optional.of(route));
        when(routeRepository.save(route)).thenReturn(route);

        Route result = routeService.updateRoute("TSA-TSB", updateRouteRequest());

        assertNotNull(result);

        verify(routeRepository).save(route);
        verify(routeHistoryRepository).save(any(RouteHistory.class));
    }

    @Test
    void updateRouteFailsWhenRouteDoesNotExist() {
        RouteID routeId = new RouteID("TSA-TSB");

        when(routeRepository.findByRouteId(routeId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.updateRoute("TSA-TSB", updateRouteRequest())
        );

        assertEquals("Route not found.", exception.getMessage());

        verify(routeRepository, never()).save(any(Route.class));
        verify(routeHistoryRepository, never()).save(any(RouteHistory.class));
    }

    @Test
    void deactivateRouteSuccessfully() {
        Route route = route();
        RouteID routeId = new RouteID("TSA-TSB");

        when(routeRepository.findByRouteId(routeId)).thenReturn(Optional.of(route));
        when(routeRepository.save(route)).thenReturn(route);

        Route result = routeService.deactivateRoute("TSA-TSB");

        assertNotNull(result);
        assertFalse(result.isActive());

        verify(routeRepository).save(route);
        verify(routeHistoryRepository).save(any(RouteHistory.class));
    }

    @Test
    void deactivateRouteFailsWhenRouteDoesNotExist() {
        RouteID routeId = new RouteID("TSA-TSB");

        when(routeRepository.findByRouteId(routeId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.deactivateRoute("TSA-TSB")
        );

        assertEquals("Route not found.", exception.getMessage());

        verify(routeRepository, never()).save(any(Route.class));
        verify(routeHistoryRepository, never()).save(any(RouteHistory.class));
    }

    @Test
    void getRouteByIdSuccessfully() {
        Route route = route();
        RouteID routeId = new RouteID("TSA-TSB");

        when(routeRepository.findByRouteId(routeId)).thenReturn(Optional.of(route));

        Route result = routeService.getRouteById("TSA-TSB");

        assertNotNull(result);
        assertEquals(new RouteID("TSA-TSB"), result.obtainRouteID());
    }

    @Test
    void getRouteByIdFailsWhenRouteDoesNotExist() {
        RouteID routeId = new RouteID("TSA-TSB");

        when(routeRepository.findByRouteId(routeId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.getRouteById("TSA-TSB")
        );

        assertEquals("Route not found.", exception.getMessage());
    }

    @Test
    void getRoutesFromAirportSuccessfully() {
        LocationIdentifier iata = new LocationIdentifier("TSA");
        Airport airport = airport("TSA", AirportStatus.OPERATIONAL);
        Route route = route();

        when(airportRepository.findByIata(iata)).thenReturn(Optional.of(airport));
        when(routeRepository.findByDeparture_Iata(iata)).thenReturn(List.of(route));

        List<Route> result = routeService.getRoutesFromAirport("TSA");

        assertEquals(1, result.size());
        verify(routeRepository).findByDeparture_Iata(iata);
    }

    @Test
    void getRoutesFromAirportFailsWhenAirportDoesNotExist() {
        LocationIdentifier iata = new LocationIdentifier("TSA");

        when(airportRepository.findByIata(iata)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.getRoutesFromAirport("TSA")
        );

        assertEquals("Airport not found.", exception.getMessage());

        verify(routeRepository, never()).findByDeparture_Iata(any(LocationIdentifier.class));
    }

    @Test
    void searchRoutesByOriginSuccessfully() {
        LocationIdentifier origin = new LocationIdentifier("TSA");
        Airport airport = airport("TSA", AirportStatus.OPERATIONAL);
        Route route = route();

        when(airportRepository.findByIata(origin)).thenReturn(Optional.of(airport));
        when(routeRepository.findByDeparture_Iata(origin)).thenReturn(List.of(route));

        List<Route> result = routeService.searchRoutes("TSA", null);

        assertEquals(1, result.size());
        verify(routeRepository).findByDeparture_Iata(origin);
    }

    @Test
    void searchRoutesByDestinationSuccessfully() {
        LocationIdentifier destination = new LocationIdentifier("TSB");
        Airport airport = airport("TSB", AirportStatus.OPERATIONAL);
        Route route = route();

        when(airportRepository.findByIata(destination)).thenReturn(Optional.of(airport));
        when(routeRepository.findByDestination_Iata(destination)).thenReturn(List.of(route));

        List<Route> result = routeService.searchRoutes(null, "TSB");

        assertEquals(1, result.size());
        verify(routeRepository).findByDestination_Iata(destination);
    }

    @Test
    void searchRoutesByOriginAndDestinationSuccessfully() {
        LocationIdentifier origin = new LocationIdentifier("TSA");
        LocationIdentifier destination = new LocationIdentifier("TSB");

        Airport originAirport = airport("TSA", AirportStatus.OPERATIONAL);
        Airport destinationAirport = airport("TSB", AirportStatus.OPERATIONAL);

        Route route = route();

        when(airportRepository.findByIata(origin)).thenReturn(Optional.of(originAirport));
        when(airportRepository.findByIata(destination)).thenReturn(Optional.of(destinationAirport));
        when(routeRepository.findByDeparture_IataAndDestination_Iata(origin, destination))
                .thenReturn(List.of(route));

        List<Route> result = routeService.searchRoutes("TSA", "TSB");

        assertEquals(1, result.size());
        verify(routeRepository).findByDeparture_IataAndDestination_Iata(origin, destination);
    }

    @Test
    void searchRoutesFailsWhenNoOriginOrDestinationIsProvided() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.searchRoutes(null, null)
        );

        assertEquals("Origin or destination is required.", exception.getMessage());
    }

    @Test
    void searchRoutesFailsWhenOriginAirportDoesNotExist() {
        LocationIdentifier origin = new LocationIdentifier("TSA");

        when(airportRepository.findByIata(origin)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.searchRoutes("TSA", null)
        );

        assertEquals("Origin airport not found.", exception.getMessage());
    }

    @Test
    void searchRoutesFailsWhenDestinationAirportDoesNotExist() {
        LocationIdentifier destination = new LocationIdentifier("TSB");

        when(airportRepository.findByIata(destination)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> routeService.searchRoutes(null, "TSB")
        );

        assertEquals("Destination airport not found.", exception.getMessage());
    }
}