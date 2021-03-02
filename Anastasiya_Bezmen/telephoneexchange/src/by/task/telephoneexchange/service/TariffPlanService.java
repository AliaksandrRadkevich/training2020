package by.task.telephoneexchange.service;

import by.task.telephoneexchange.domain.TariffPlan;
import by.task.telephoneexchange.repository.ITariffPlanRepository;
import by.task.telephoneexchange.repository.RepositoryException;

import java.util.List;

/**
 * TariffPlanService.
 * Date: 01/28/2021
 *
 * @author Anastasiya Bezmen
 */
public class TariffPlanService implements ITariffPlanService {

    private ITariffPlanRepository tariffPlanRepository;

    public void setTariffPlanRepository(ITariffPlanRepository tariffPlanRepository) {
        this.tariffPlanRepository = tariffPlanRepository;
    }

    @Override
    public List<TariffPlan> getAll() throws ServiceException {
        try {
            return tariffPlanRepository.readAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
