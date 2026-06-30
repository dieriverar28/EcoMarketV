package com.example.MicroPedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroPedido.entity.DetallePedido;
import com.example.MicroPedido.repository.DetallePedidoRepository;
import com.example.MicroPedido.service.impl.DetallePedidoServiceImpl;

@ExtendWith(MockitoExtension.class)
class DetallePedidoServiceImplTest {

    @Mock
    private DetallePedidoRepository detallePedidoRepository;

    @InjectMocks
    private DetallePedidoServiceImpl detallePedidoService;

    @Test
    void listarDetalles() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId_detalle(1);
        detalle.setCantidad(2);
        detalle.setPrecio_unitario(5000);
        when(detallePedidoRepository.obtenerDetallePedidos()).thenReturn(List.of(detalle));

        List<DetallePedido> result = detallePedidoService.getDetallesPedido();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCantidad()).isEqualTo(2);
    }

    @Test
    void buscarDetallePorId() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId_detalle(2);
        detalle.setCantidad(3);
        detalle.setPrecio_unitario(7000);
        when(detallePedidoRepository.buscarDetallePedido(2)).thenReturn(detalle);

        DetallePedido result = detallePedidoService.getDetallePedidoById(2);

        assertThat(result.getId_detalle()).isEqualTo(2);
        assertThat(result.getPrecio_unitario()).isEqualTo(7000);
    }

    @Test
    void guardarDetalle() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId_detalle(3);
        detalle.setCantidad(4);
        detalle.setPrecio_unitario(8000);
        when(detallePedidoRepository.save(detalle)).thenReturn(detalle);

        DetallePedido result = detallePedidoService.saveDetallePedido(detalle);

        assertThat(result).isEqualTo(detalle);
        verify(detallePedidoRepository).save(detalle);
    }

    @Test
    void actualizarDetalle() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId_detalle(4);
        detalle.setCantidad(5);
        detalle.setPrecio_unitario(9000);
        when(detallePedidoRepository.modificarDetallePedido(4, 5, 9000)).thenReturn(1);

        int result = detallePedidoService.updateDetallePedido(detalle);

        assertThat(result).isEqualTo(1);
        verify(detallePedidoRepository).modificarDetallePedido(4, 5, 9000);
    }

    @Test
    void eliminarDetalle() {
        when(detallePedidoRepository.eliminarDetallePedido(5)).thenReturn(1);

        int result = detallePedidoService.deleteDetallePedido(5);

        assertThat(result).isEqualTo(1);
        verify(detallePedidoRepository).eliminarDetallePedido(5);
    }
}
